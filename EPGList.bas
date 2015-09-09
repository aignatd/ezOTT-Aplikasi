Type=Activity
Version=5.02
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: False
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

	Dim ChaCode			As String
	Dim ColCode			As String
	Dim lstEPG			As List
	Dim PlayEPG			As List
	Dim lstPrev			As List
	Dim BmpPre			As Object
	Dim Aktif			As Int
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	
	Private ivChaName 		As ImageView
	Private BDEPGList		As BetterDialogs	
	Private GifEPG 			As MovieViewControl
	Private lvEPG 			As ListView
	Private bmpTemp			As BitmapDrawable
	Private bmpImage		As ImageView
	Private bmpFav			As ImageView
	Private ivBackEPG 		As ImageView

	#Region Variable instal player
	Private ups 				As Intent
	Private ipack				As Int
	Private pm 					As PackageManager
	#End Region
	
	#Region Detail EPG
	Private pEPGDet 		As Panel
	Private ivEPGDet 		As ImageView
	Private lblEPGDet 		As Label
	Private svEPG 			As ScrollView
	#End Region
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	
	Activity.LoadLayout("EPGList")
	
	GifEPG.Load(File.DirAssets, "spin.gif", True)
	GifEPG.Visible = True

	If FirstTime Then
		Try
			Fungsi.SetDirProg("")
			Fungsi.BacaFileConfig
			Fungsi.mpList.Put("DirDBS", Fungsi.DirDBS)
		Catch
			BDEPGList.Msgbox(Fungsi.TitleBox, Fungsi.InitFail, Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
			ExitApplication
		End Try
	End If

	#Region Cek File Config.json
	If File.Exists(File.DirAssets, "config.json") Then
		If Fungsi.CacahJSON(File.ReadString(File.DirAssets, "config.json"), "NextArray") = False Then
			BDEPGList.Msgbox(Fungsi.TitleBox, Fungsi.InitFail, Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
			ExitApplication
		End If
	Else
		BDEPGList.Msgbox(Fungsi.TitleBox, Fungsi.LoadFail, Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
		ExitApplication
	End If
	#End Region

	Dim pacList As List = pm.GetInstalledPackages
	ipack = pacList.IndexOf("com.nexstreaming.app.nexplayersample")

	If ipack < 0 Then
		ups.Initialize(ups.ACTION_VIEW, "file://" & File.Combine(Fungsi.DirDBS, "nexplayersdklitevm.apk"))
		ups.SetType("application/vnd.android.package-archive")
		StartActivity(ups)
	End If

	bmpTemp.Initialize(BmpPre)
	bmpTemp.Gravity = Gravity.CENTER

	ivChaName.Bitmap = bmpTemp.Bitmap
	ivChaName.Gravity = Gravity.FILL
	
	bmpImage.Initialize("")
	bmpImage.Background = Fungsi.GetDrawable("icoplay")
	bmpImage.Gravity = Gravity.FILL
	

	ivBackEPG.Background = Fungsi.GetDrawable("icoback")
	init_listEPG
End Sub

Sub Activity_Resume
	Fungsi.BacaFileConfig
	Fungsi.DirDBS = Fungsi.mpList.Get("DirDBS")

	If ipack < 0 Then
		BDEPGList.Msgbox(Fungsi.TitleBox, Fungsi.NoPlayer, Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
	Else
		ups.Initialize(ups.ACTION_MAIN, "")
		ups = pm.GetApplicationIntent("com.nexstreaming.app.nexplayersample")
		ups.PutExtra("Company", "PT-Infokom")
		ups.PutExtra("VCASAddr", "202.80.222.36:80")
	End If
End Sub

Sub init_listEPG
	Private strCMD 	As String
	Private ChnJob 	As MNCHttpJob
	
	CallSub(ImageDownloader, "ActivityIsPaused")
	CallSub(EPGDownloader, "ActivityIsPaused")

	StopService(MNCUtils2Service)
	CancelScheduledService(MNCUtils2Service)
	StopService(ImageDownloader)
	CancelScheduledService(ImageDownloader)
	StopService(EPGDownloader)
	CancelScheduledService(EPGDownloader)

	If Aktif = 1 Then
		strCMD = Fungsi.mpList.Get("URLLiveTVEPG") 
	Else
		strCMD = Fungsi.mpList.Get("URLListEPG")
	End If
	
	strCMD = strCMD & Fungsi.mpList.Get("channelcode") & ChaCode & "&" & Fungsi.mpList.Get("columncode") & ColCode
	ChnJob.Initialize("GetListEPG", Me)

	Try
		ChnJob.Download(strCMD)
		ChnJob.GetRequest.SetHeader("Cache-Control", "no-cache, must-revalidate")
		ChnJob.GetRequest.Timeout = DateTime.TicksPerSecond * 60
		ChnJob.Tag = strCMD
	Catch
		ChnJob.Release
		Fungsi.mpList.Put("List EPG", "No List EPG")
		BDEPGList.Msgbox(Fungsi.TitleBox, Fungsi.NoEPGList, Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
	End Try	
End Sub

Sub Activity_Pause (UserClosed As Boolean)
	CallSub(ImageDownloader, "ActivityIsPaused")
	CallSub(EPGDownloader, "ActivityIsPaused")
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'return true if you want to consume the event
	If KeyCode = 4 Then
		GifEPG.Visible = False
		CallSub(ImageDownloader, "ActivityIsPaused")
		CallSub(EPGDownloader, "ActivityIsPaused")

		StopService(MNCUtils2Service)
		CancelScheduledService(MNCUtils2Service)
		StopService(ImageDownloader)
		CancelScheduledService(ImageDownloader)
		StopService(EPGDownloader)
		CancelScheduledService(EPGDownloader)

		Activity.Finish
	End If

	Return True
End Sub

Sub JobDone(MNChttp As MNCHttpJob)
	Dim sf				As StringFunctions
	Dim parser 		As JSONParser	
	Dim mresult		As Map
	Dim i 				As Int
	Dim ListEPG		As List
	Dim ListDesc	As List
	
	StopService(MNCUtils2Service)
	CancelScheduledService(MNCUtils2Service)
	
	#Region HTTP Gagal
	If MNChttp.Success = False Then
		If (sf.InString(MNChttp.ErrorMessage, "timed out") > 0) Or (sf.InString(MNChttp.ErrorMessage, "refused") > 0) Or _
			 (sf.InString(MNChttp.ErrorMessage, "Unable to resolve host") > 0) Or (sf.InString(MNChttp.ErrorMessage, "UnknownHostException") > 0) Or _
			 (sf.InString(MNChttp.ErrorMessage, "FileNotFound") > 0) Then
			GifEPG.Visible = False
			BDEPGList.Msgbox(Fungsi.TitleBox, Fungsi.NoKoneksi, Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
		Else
			If sf.InString(MNChttp.ErrorMessage, "Not Found") < 0 Then
				GifEPG.Visible = False
				BDEPGList.Msgbox(Fungsi.TitleBox, Fungsi.Unknown, Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
			Else
				If MNChttp.JobName = "Koneksi" Then
					MNChttp.Release
					'ProsesAllMenu
				End If
			End If
		End If

		Fungsi.mpList.Put("Koneksi", "Offline")
		MNChttp.Release
	#End Region
	#Region HTTP Sukses
	Else
		Fungsi.mpList.Put("Koneksi", "Online")

		Select MNChttp.JobName			
			#Region List EPG
			Case "GetListEPG"
				Try
					parser.Initialize(MNChttp.GetString)
					MNChttp.Release
				Catch
					GifEPG.Visible = False
					BDEPGList.Msgbox(Fungsi.TitleBox, Fungsi.FailJSON, Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
					Return
				End Try
				
				Try
					mresult.Initialize
					mresult = parser.NextObject
				Catch
					GifEPG.Visible = False
					BDEPGList.Msgbox(Fungsi.TitleBox, Fungsi.FailProses, Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
					Return
				End Try

				If mresult.Get("returncode") <> "0" Then
					MNChttp.Release
					GifEPG.Visible = False
					BDEPGList.Msgbox(Fungsi.TitleBox, mresult.Get("errormsg"), Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
				Else
					Dim lstBackup As List
					
					lstBackup.Initialize
					
					ListEPG 	= mresult.Get("begintime")
					ListDesc 	= mresult.Get("programdescription")
					lstPrev 	= mresult.Get("prevuecode")

					For g=0 To ListEPG.Size-1
						lstBackup.Add(ListEPG.Get(g) & "#" & ListDesc.Get(g) & "#" & lstPrev.Get(g))
					Next

					lstBackup.Sort(False)
					ListEPG.Clear
					ListDesc.Clear
					lstPrev.Clear

					For h=0 To lstBackup.Size-1
						Dim strBackup() As String						
						strBackup = Regex.Split("#", lstBackup.Get(h))
						ListEPG.Add(strBackup(0))
						ListDesc.Add(strBackup(1))
						lstPrev.Add(strBackup(2))
					Next

					svEPG.Panel.RemoveAllViews
					
					For i=0 To ListEPG.Size-2
						Dim PanEPG 				As Panel
						Dim Waktu				As List
						Dim Tanggal				As String
						
						PanEPG.Initialize("")
						PanEPG.LoadLayout("EPGDetail")
						
						If Aktif <> 1 Then
							ivEPGDet.Background = bmpImage.Background
							ivEPGDet.Tag = lstPrev.Get(i)
							ivEPGDet.Visible = True
						Else
							ivEPGDet.Visible = False
						End If
						
						svEPG.Panel.AddView(PanEPG, 0, i * lblEPGDet.Height, svEPG.Width, lblEPGDet.Height)
						
						If (i Mod 20) = 0 Then DoEvents
						
						Waktu 	= sf.Split(ListEPG.Get(i), " ")
						Tanggal	= sf.Right(Waktu.Get(0), 2) & "." & sf.Mid(Waktu.Get(0), 6, 2) & "." & sf.Left(Waktu.Get(0), 4)
						
						lblEPGDet.Text = ListDesc.Get(i) & CRLF & Tanggal & " | " & Waktu.Get(1)	
					Next

					svEPG.Panel.Height = lblEPGDet.Height * (ListEPG.Size-1)
					GifEPG.Visible = False
				End If
			#End Region
			#Region URL video file
			Case "GetPrevEPG"
				DoEvents
				GifEPG.Visible=True
				If ipack < 0 Then
					GifEPG.Visible = False
					BDEPGList.Msgbox(Fungsi.TitleBox, Fungsi.NoPlayer, Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
				Else
					GifEPG.Visible = False
					ups.PutExtra("LinkURL", MNChttp.GetString)
					If ups.IsInitialized Then StartActivity(ups)
				End If
			#End Region
		End Select

		MNChttp.Release
	End If
	#End Region
End Sub

Sub lvEPG_ItemClick (Position As Int, Value As Object)
	Dim strCMD 	As String
	Dim ChnJob 	As MNCHttpJob

	StopService(MNCUtils2Service)
	CancelScheduledService(MNCUtils2Service)
	
	strCMD = Fungsi.mpList.Get("URLMovieEPG") & Fungsi.mpList.Get("chancode") & ChaCode & "&" & Fungsi.mpList.Get("pcode") & lstPrev.Get(Position)
	ChnJob.Initialize("GetPrevEPG", Me)

	Try
		ChnJob.Download(strCMD)
		ChnJob.GetRequest.SetHeader("Cache-Control", "no-cache, must-revalidate")
		ChnJob.GetRequest.Timeout = DateTime.TicksPerSecond * 60
		ChnJob.Tag = strCMD
	Catch
		ChnJob.Release
		Fungsi.mpList.Put("Movie EPG", "No Movie EPG")
		BDEPGList.Msgbox(Fungsi.TitleBox, Fungsi.NoMovie, Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
	End Try	
End Sub

Sub ivEPGDet_Click
	Dim ivTmp		As ImageView = Sender
	Dim strCMD 	As String
	Dim ChnJob 	As MNCHttpJob
	
	ivEPGDet.Visible = True
	CallSub(ImageDownloader, "ActivityIsPaused")
	CallSub(EPGDownloader, "ActivityIsPaused")

	StopService(MNCUtils2Service)
	CancelScheduledService(MNCUtils2Service)
	StopService(ImageDownloader)
	CancelScheduledService(ImageDownloader)
	StopService(EPGDownloader)
	CancelScheduledService(EPGDownloader)
	
	strCMD = Fungsi.mpList.Get("URLMovieEPG") & Fungsi.mpList.Get("chancode") & ChaCode & "&" & Fungsi.mpList.Get("pcode") & ivTmp.Tag
	ChnJob.Initialize("GetPrevEPG", Me)

	Try
		ChnJob.Download(strCMD)
		ChnJob.GetRequest.SetHeader("Cache-Control", "no-cache, must-revalidate")
		ChnJob.GetRequest.Timeout = DateTime.TicksPerSecond * 60
		ChnJob.Tag = strCMD
	Catch
		ChnJob.Release
		Fungsi.mpList.Put("Movie EPG", "No Movie EPG")
		BDEPGList.Msgbox(Fungsi.TitleBox, Fungsi.NoMovie, Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
	End Try	
		
End Sub

Sub ivBackEPG_Click
	Activity_KeyPress(4)	
End Sub