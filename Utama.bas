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

End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Dim		myMap As		Map
	Dim myphone		As Phone

	#Region Untuk cek screen device
	Private Spasi			As Int
	Private Lebar			As Int
	Private Tinggi			As Int
	Private Durasi			As Int
	Private jeday			As Int
	Private Device			As Int
	#End Region
	
	Private BDUtama			As BetterDialogs	
	Private pbMain 			As Panel
	Private MNCJob 			As MNCHttpJob
	Private lblFeat 		As Label
	Private GifSmart 		As MovieViewControl
	Private paMain 			As Panel
	Private pTengah 		As Panel
	Private cvThumDet 		As MovieViewControl
	Private picture			As Map
	
	#Region Map penampung data
	Private MapTampung		As Map
	#End Region

	#Region Variable instal player
	Private ups 				As Intent
	Private ipack				As Int
	Private pm 					As PackageManager
	#End Region
	
	#Region Variable List LiveTV
	Private ivEPGDet	 	As ImageView
	Private ivEPGDet2		As ImageView
	Private ivEPGDet3		As ImageView
	Private lblEPGDet 		As Label
	Private ivThumDet 		As ImageView
	Private bmpTemp			As ImageView
	Private bmpFav			As ImageView
	Private bmpDelFav		As ImageView
	#End Region
	
	#Region Variable tombol bawah di menu utama
	Private ivLiveTV 		As ImageView
	Private ivTVOD 			As ImageView
	Private ivVOD 			As ImageView
	Private ivFav 			As ImageView
	Private lblGenre 		As Label
	#End Region
	
	#Region Favorite Menu
	Dim 	User			As Map
	Dim		Fav				As Map
	Dim 	RAF				As RandomAccessFile
	#End Region
	
	Dim		mlbFav			As Map
	Dim		keyFav			As KeyValueStore
	Dim		whitecolor		As Int = Colors.White
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	
			mlbFav.Initialize
			
			If File.Exists(File.DirDefaultExternal, Fungsi.DataConfig.Username & "_label") Then
				mlbFav = File.ReadMap(File.DirDefaultExternal, Fungsi.DataConfig.Username & "_label")
			End If

			keyFav.Initialize(File.DirDefaultExternal, Fungsi.DataConfig.Username & "_gambar")


	If FirstTime Then
		Try
			
			Fungsi.SetDirProg("")
			Fungsi.BacaFileConfig
			Fungsi.mpList.Put("DirDBS", Fungsi.DirDBS)
		Catch
			BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.InitFail, Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
			ExitApplication
		End Try
	End If
	
	#Region Deteksi screen layar gadget
	If GetDeviceLayoutValues.ApproximateScreenSize < 5 Then
 		'phones
   	If 100%x > 100%y Then
			Spasi = -22
			Lebar = 45%x
			Tinggi = 35%y
			Durasi = 800
			jeday = 0.2%y
		Else
			Spasi = -17
			Lebar = 45%x
			Tinggi = 35%y
			Durasi = 800
			jeday = 0.5%y
	End If
		
		Device = 0
		Activity.LoadLayout("smartphone")
		
	Else
  	'tablets
		Spasi = -75
		Lebar = 50%x
		Tinggi = 40%y
		Durasi = 700
		jeday = 0.5%y

		Device = 1
		Activity.LoadLayout("tablet")
	End If
	
	#End Region
	
	#Region Cek File Config.json
	If File.Exists(File.DirAssets, "config.json") Then
		If Fungsi.CacahJSON(File.ReadString(File.DirAssets, "config.json"), "NextArray") = False Then
			BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.InitFail, Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
			ExitApplication
		End If
	Else
		BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.LoadFail, Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
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

#Region bmp ico
	bmpTemp.Initialize("")
	bmpTemp.Background = Fungsi.GetDrawable("icoplay")
	bmpTemp.Gravity = Gravity.FILL
	
	bmpFav.Initialize("")
	bmpFav.Background = Fungsi.GetDrawable("ico_favorite")
	bmpFav.Gravity = Gravity.FILL
	
	bmpDelFav.Initialize("")
	bmpDelFav.Background = Fungsi.GetDrawable("ico_delfav")
	bmpDelFav.Gravity = Gravity.FILL
#End Region

	GifSmart.Load(File.DirAssets, "spin.gif", True)
	GifSmart.Visible = False
	MapTampung.Initialize
	picture.Initialize
	
	init_layout
End Sub

Sub Activity_Resume
	Fungsi.BacaFileConfig
	Fungsi.DirDBS = Fungsi.mpList.Get("DirDBS")

	If ipack < 0 Then
		BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.NoPlayer, Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
	Else
		ups.Initialize(ups.ACTION_MAIN, "")
		ups = pm.GetApplicationIntent("com.nexstreaming.app.nexplayersample")
		ups.PutExtra("Company", "PT-Infokom")
		ups.PutExtra("VCASAddr", "202.80.222.36:80")
	End If

	Cek_Koneksi
	
	
End Sub

Sub Activity_Pause (UserClosed As Boolean)
	CallSub(ImageDownloader, "ActivityIsPaused")
	CallSub(EPGDownloader, "ActivityIsPaused")
End Sub

Sub init_layout
	DoEvents
	
	lblGenre.Background = Fungsi.GetDrawable("mnc_top")

	Select Fungsi.DataConfig.sTab
	Case "LiveTV"
		lblFeat.Background = Fungsi.GetDrawable("titlelivetv")
		ivLiveTV.Background = Fungsi.GetDrawable("livetvwhite")
		ivTVOD.Background = Fungsi.GetDrawable("tvod")
		ivVOD.Background = Fungsi.GetDrawable("vod")
		ivFav.Background = Fungsi.GetDrawable("favoriteblack")
	Case "TVOD"
		lblFeat.Background = Fungsi.GetDrawable("titletvod")
		ivTVOD.Background = Fungsi.GetDrawable("tvodwhite")
		ivLiveTV.Background = Fungsi.GetDrawable("livetv")
		ivVOD.Background = Fungsi.GetDrawable("vod")
		ivFav.Background = Fungsi.GetDrawable("favoriteblack")
	Case "VOD"
		lblFeat.Background = Fungsi.GetDrawable("titlevod")
		ivTVOD.Background = Fungsi.GetDrawable("tvod")
		ivLiveTV.Background = Fungsi.GetDrawable("livetv")
		ivVOD.Background = Fungsi.GetDrawable("vodwhite")
		ivFav.Background = Fungsi.GetDrawable("favoriteblack")
	Case "Favorite"
		lblFeat.Background = Fungsi.GetDrawable("titlefav")
		ivTVOD.Background = Fungsi.GetDrawable("tvod")
		ivLiveTV.Background = Fungsi.GetDrawable("livetv")
		ivVOD.Background = Fungsi.GetDrawable("vod")
		ivFav.Background = Fungsi.GetDrawable("favoritewhite")
	End Select

	DoEvents
End Sub

Sub ivLiveTV_Click
	If Fungsi.DataConfig.sTab <> "LiveTV" Then 
		pTengah.RemoveAllViews
		CallSub(ImageDownloader, "ActivityIsPaused")

		DoEvents	
		Fungsi.DataConfig.sTab = "LiveTV"
		Fungsi.tulisfileconfig(Fungsi.DataConfig)
		init_layout
		Cek_Koneksi	
	End If
End Sub

Sub ivFav_Click	
	If Fungsi.DataConfig.sTab <> "Favorite" Then
		pTengah.RemoveAllViews
		CallSub(ImageDownloader, "ActivityIsPaused")
		
		
		DoEvents
		Fungsi.DataConfig.sTab = "Favorite"
		Fungsi.TulisFileConfig(Fungsi.DataConfig)
		init_layout
		Cek_Koneksi
'		GifSmart.Visible = False
	End If
End Sub

Sub Cek_Koneksi
	GifSmart.Visible = True

	CallSub(ImageDownloader, "ActivityIsPaused")
	CallSub(EPGDownloader, "ActivityIsPaused")

	StopService(MNCUtils2Service)
	CancelScheduledService(MNCUtils2Service)
	StopService(ImageDownloader)
	CancelScheduledService(ImageDownloader)
	StopService(EPGDownloader)
	CancelScheduledService(EPGDownloader)

	MNCJob.Initialize("Koneksi", Me)

	Try
		MNCJob.Download("http://clips.mncplaymedia.net")
	Catch
		MNCJob.Release
		Fungsi.mpList.Put("Koneksi", "Offline")
		GifSmart.Visible = False
		BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.NoKoneksi, Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
	End Try
End Sub


Sub JobDone(MNChttp As MNCHttpJob)
	Dim sf				As StringFunctions
	Dim parser 		As JSONParser	
	Dim mresult		As Map
	Dim i 				As Int
	Dim ListVOD		As List
	Dim PicVOD		As List

	Dim sTmp 			As String
	Dim bTmp()		As Byte
	Dim bcTmp			As ByteConverter
	Dim su 				As StringUtils

	StopService(MNCUtils2Service)
	CancelScheduledService(MNCUtils2Service)
	StopService(ImageDownloader)
	CancelScheduledService(ImageDownloader)
	StopService(EPGDownloader)
	CancelScheduledService(EPGDownloader)

	#Region HTTP Gagal
	If MNChttp.Success = False Then
		If (sf.InString(MNChttp.ErrorMessage, "timed out") > 0) Or (sf.InString(MNChttp.ErrorMessage, "refused") > 0) Or _
			 (sf.InString(MNChttp.ErrorMessage, "Unable to resolve host") > 0) Or (sf.InString(MNChttp.ErrorMessage, "UnknownHostException") > 0) Or _
			 (sf.InString(MNChttp.ErrorMessage, "FileNotFound") > 0) Then
			GifSmart.Visible = False
			BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.NoKoneksi, Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
		Else
			If sf.InString(MNChttp.ErrorMessage, "Not Found") < 0 Then
				GifSmart.Visible = False
				BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.Unknown, Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
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
			#Region Cek Koneksi
			Case "Koneksi"
				MNChttp.Release

				Select Fungsi.DataConfig.sTab
				Case "TVOD"
					GetListTVOD
				Case "LiveTV"
					GetListLiveTV
				Case "VOD"
					GetListVOD
				Case "Favorite"
					GetListFavorite
				End Select
				
			#End Region
			#Region Get List TVOD, VOD, Favorite dan LiveTV
			Case "GetListTVOD", "GetListLiveTV"				
				#Region Parsing JSON
				Try
					parser.Initialize(MNChttp.GetString)
					MNChttp.Release
				Catch
					GifSmart.Visible = False
					BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.FailJSON, Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
					Return
				End Try
				
				Try
					mresult.Initialize
					mresult = parser.NextObject
				Catch
					GifSmart.Visible = False
					BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.FailProses, Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
					Return
				End Try
				#End Region

				If mresult.Get("returncode") <> "0" Then
					#Region Return Code Gagal
					GifSmart.Visible = False
					BDUtama.Msgbox(Fungsi.TitleBox, mresult.Get("errormsg"), Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
					#End Region
				Else
					#Region Return Code Sukses
					pTengah.RemoveAllViews
										
					ListVOD = mresult.Get("channelname")
					PicVOD 	= mresult.Get("filename")
					
					Select MNChttp.JobName
					Case "GetListTVOD"
						#Region Tampilan list untuk TVOD

						#Region Photogridview berdasarkan orientation
						Dim pgvUtama As PhotoGridView

						pgvUtama.Initialize("pgvEvent")
						pgvUtama.CacheInMemory = True
						pgvUtama.CacheOnDisk = True
						pgvUtama.CompressQuality = 90
						pgvUtama.RoundedBitmap = False
					 	pgvUtama.Gravity=Gravity.CENTER
						pgvUtama.Enabled = True
						pgvUtama.ProgressBarIndeterminate = False
						pgvUtama.ProgressBarVisible = True
						pgvUtama.ScaleType = pgvUtama.ScaleType.Fit_Center
					
						If Device = 0 Then
							If Activity.Width > Activity.Height Then
								pgvUtama.NumColumns = 4
							Else
								pgvUtama.NumColumns = 3
							End If

					    pgvUtama.HorizontalSpacing = 3%x
					    pgvUtama.VerticalSpacing = 0.5%y
					    pgvUtama.ItemWidth = 40%x
					    pgvUtama.ItemHeight = 120%y
						Else
							If Activity.Width > Activity.Height Then
								pgvUtama.NumColumns = 5
							Else
								pgvUtama.NumColumns = 4
							End If
							
					    pgvUtama.HorizontalSpacing = 3%x
					    pgvUtama.VerticalSpacing = 0.5%y
					    pgvUtama.ItemWidth = 40%x
					    pgvUtama.ItemHeight = 120%y
						End If
						#End Region
															
						MapTampung.Clear
						MapTampung.Put("channelname", mresult.Get("channelname"))
						MapTampung.Put("channelcode", mresult.Get("channelcode"))
						MapTampung.Put("columncode", "0000")
						MapTampung.Put("pgvUtama", pgvUtama)

						For i=0 To ListVOD.Size-1
							pgvUtama.AddImageFromWeb(Fungsi.mpList.Get("PicListVOD") & PicVOD.Get(i))
						Next

						pTengah.AddView(pgvUtama, 2%x, 0, pTengah.Width - 4%x, pTengah.Height - pbMain.Height)
						#End Region
					Case "GetListLiveTV"
						#Region Tampilan list untuk LiveTV
						Dim svTengah 		As ScrollView
						Dim mapImage		As Map
						Dim mapEPG			As Map
						Dim lstchacode	As List
						Dim strcolcode	As String
						Dim cekscreen	As String
						
						
						mapImage.Initialize
						mapImage.Clear
						mapEPG.Initialize
						mapEPG.Clear
						
						svTengah.Initialize(0)
						svTengah.Panel.RemoveAllViews
						pTengah.AddView(svTengah, 0, 1.5%y, pTengah.Width, pTengah.Height - 3%y)
						
						lstchacode = mresult.Get("channelcode")
						strcolcode = "0000"
						
						For i=0 To ListVOD.Size-1
							Dim PanEPG 				As Panel
							
							PanEPG.Initialize("")
							
							If Device = 0 Then
								PanEPG.LoadLayout("LiveTVDetSmart")
							Else
								PanEPG.LoadLayout("LiveTVDetail")
							End If
							
							svTengah.Panel.AddView(PanEPG, 0, (i*lblEPGDet.Height) + (i*1.5%y), svTengah.Width, lblEPGDet.Height)
	
							If (i Mod 30) = 0 Then DoEvents
										
							ivEPGDet.Background = bmpTemp.Background
							ivEPGDet2.Background = bmpFav.Background
							

							ivEPGDet.Tag = Fungsi.mpList.Get("URLLiveTV") & Fungsi.mpList.Get("LiveTVchancode") & lstchacode.Get(i) & "&" & Fungsi.mpList.Get("LiveTVcolcode") & strcolcode & "#Detail"	
							ivEPGDet2.Tag = "Putih#None"
							ivThumDet.Tag = Fungsi.mpList.Get("URLLiveTV") & Fungsi.mpList.Get("LiveTVchancode") & lstchacode.Get(i) & "&" & Fungsi.mpList.Get("LiveTVcolcode") & strcolcode & "#Thumb#" & lstchacode.Get(i) & "#" & strcolcode
							lblEPGDet.Tag = lstchacode.Get(i) & "#" & strcolcode
							
							mapImage.Put(ivThumDet, Fungsi.mpList.Get("PicListVOD") & sf.Mid(PicVOD.Get(i), 4, sf.Len(PicVOD.Get(i)) - 3))
							mapEPG.Put(lblEPGDet, Fungsi.mpList.Get("EPGHour") & Fungsi.mpList.Get("Hourchancode") & lstchacode.Get(i) & "&" & Fungsi.mpList.Get("Hourcolcode") & strcolcode)

'							cvThumDet.Initialize("")
'							cvThumDet.Load(File.DirAssets, "pleasewait.jpg", True)
'							cvThumDet.Visible = True
						Next
						
						CallSubDelayed2(ImageDownloader, "Download", mapImage)
						CallSubDelayed2(EPGDownloader, "Download", mapEPG)
						svTengah.Panel.Height = (lblEPGDet.Height * ListVOD.Size) + (ListVOD.Size * 1.5%y) + pbMain.Height
						#End Region
					

					End Select
								
								GifSmart.Visible = False
								#End Region
					End If				
					#End Region
			#Region Get URL LiveTV
			Case "GetURLLiveTV"
				GifSmart.Visible = False
				
				If ipack < 0 Then
					BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.NoPlayer, Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
				Else
					ups.PutExtra("LinkURL", MNChttp.GetString)
					If ups.IsInitialized Then StartActivity(ups)
				End If
			#End Region
			#Region Get List VOD
			
			Case "GetListVOD"			
				Try
					bTmp = su.DecodeBase64(MNChttp.GetString)
					sTmp = bcTmp.StringFromBytes(bTmp, "UTF8")
					
					parser.Initialize(Fungsi.UnWrap(sTmp))
					MNChttp.Release
				Catch
					BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.FailProses, Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
					Return
				End Try
				
				Try
					mresult.Initialize
					mresult = parser.NextObject
				Catch
					BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.FailProses, Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
					Return
				End Try

				If mresult.Get("Result") <> "0" Then
					BDUtama.Msgbox(Fungsi.TitleBox, mresult.Get("Message"), Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
				Else							
					Dim New 			As Map
					Dim Top 			As List
					Dim SubKiri 		As String
					Private k1,k2		As Int

					New.Initialize
					New = mresult.Get("Data")

					Top.Initialize

					SubKiri = New.Get("subcategory")
					Top = New.Get("data")
			
					pTengah.RemoveAllViews

					#Region Photogridview berdasarkan orientation
					Dim pgvUtama As PhotoGridView
					
					pgvUtama.Initialize("pgvEventVOD")
					pgvUtama.CacheInMemory = True
					pgvUtama.CacheOnDisk = True
					pgvUtama.CompressQuality = 90
					pgvUtama.RoundedBitmap = False
				  	pgvUtama.Gravity=Gravity.FILL
					pgvUtama.Enabled = True
					pgvUtama.ProgressBarIndeterminate = False
					pgvUtama.ProgressBarVisible = True
					pgvUtama.ScaleType = pgvUtama.ScaleType.Fit_XY
				
					If Device = 0 Then
						If Activity.Width > Activity.Height Then
							pgvUtama.NumColumns = 4
				    	pgvUtama.VerticalSpacing = 7%y
						Else
							pgvUtama.NumColumns = 3
				    	pgvUtama.VerticalSpacing = 5%y
						End If

				    pgvUtama.HorizontalSpacing = 2%x
				    pgvUtama.ItemWidth = 40%x
				    pgvUtama.ItemHeight = 120%y
					Else
						If Activity.Width > Activity.Height Then
							pgvUtama.NumColumns = 4
				    	pgvUtama.VerticalSpacing = 7%y
						Else
							pgvUtama.NumColumns = 3
				    	pgvUtama.VerticalSpacing = 5%y
						End If
						
				    pgvUtama.HorizontalSpacing = 2%x
				    pgvUtama.ItemWidth = 60%x
				    pgvUtama.ItemHeight = 20%y
					End If
					#End Region
						
					#Region Ambil gambar VOD tampilkan di photogridview
					Dim Contentvideo 	As Map
					Dim i 			 			As Int=0
			
					Contentvideo.Initialize
					Contentvideo.Clear
					picture.clear
			
					For Each colKiri As Map In Top
						For x2=0 To colKiri.Size-1
							Select colKiri.GetKeyAt(x2)
							Case "slug"
								pgvUtama.AddImageFromWeb(Fungsi.mpList.Get("PicVOD") & colKiri.Get("slug") & "/poster.jpg")
							End Select
						Next
						
						picture.Put(i, colKiri)
						i = i + 1						
					Next
								
					pTengah.AddView(pgvUtama, 2%x, 2%y, pTengah.Width - 4%x, (pTengah.Height - pbMain.Height) - 4%y)
					GifSmart.Visible = False
					#End Region
				End If
			#End Region
		End Select
	End If
	#End Region
End Sub

Sub ivTVOD_Click
	If Fungsi.DataConfig.sTab <> "TVOD" Then pTengah.RemoveAllViews

	CallSub(ImageDownloader, "ActivityIsPaused")

	DoEvents	
	Fungsi.DataConfig.sTab = "TVOD"
	Fungsi.tulisfileconfig(Fungsi.DataConfig)
	init_layout
	Cek_Koneksi	
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'return true if you want to consume the event
	
	If KeyCode = 4 Then ' Back Key
	
		CallSub(ImageDownloader, "ActivityIsPaused")
		CallSub(EPGDownloader, "ActivityIsPaused")

		StopService(MNCUtils2Service)
		CancelScheduledService(MNCUtils2Service)
		StopService(ImageDownloader)
		CancelScheduledService(ImageDownloader)
		StopService(EPGDownloader)
		CancelScheduledService(EPGDownloader)
		
		File.WriteMap(File.DirDefaultExternal, Fungsi.DataConfig.Username & "_label", mlbFav)
		
		StartActivity(Pilihan)
		Activity.Finish
	Else
		Return True
	End If
End Sub

Sub GetListTVOD
	Dim strCMD 	As String
	Dim ChnJob 	As MNCHttpJob
	
	strCMD = Fungsi.mpList.Get("URLListVOD")
	ChnJob.Initialize("GetListTVOD", Me)

	Try
		ChnJob.Download(strCMD)
		ChnJob.GetRequest.SetHeader("Cache-Control", "no-cache, must-revalidate")
		ChnJob.GetRequest.Timeout = DateTime.TicksPerSecond * 60
		ChnJob.Tag = strCMD
	Catch
		ChnJob.Release
		Fungsi.mpList.Put("Channel", "No Channel View")
		BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.NoGetList, Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
	End Try	
End Sub

Sub pgvEvent_ItemClick (Position As Int, Value As Object)
	Dim lstChaCode		As List
	Dim strColCode		As String
	
	lstChaCode.Initialize
	lstChaCode.Clear
	
	lstChaCode	=	MapTampung.Get("channelcode")
	strColCode	= MapTampung.Get("columncode")	
	
	If Fungsi.DataConfig.sTab = "TVOD" Then
		EPGList.ColCode = strColCode
		EPGList.ChaCode =	lstChaCode.Get(Position)		
		EPGList.BmpPre = Value
		EPGList.Aktif = 2
		StartActivity("EPGList")
	Else
		Dim strCMD 	As String
		Dim ChnJob 	As MNCHttpJob
		
		strCMD = Fungsi.mpList.Get("URLLiveTV") & Fungsi.mpList.Get("LiveTVchancode") & lstChaCode.Get(Position) & "&" & Fungsi.mpList.Get("LiveTVcolcode") & strColCode
		ChnJob.Initialize("GetURLLiveTV", Me)

		Try
			ChnJob.Download(strCMD)
			ChnJob.GetRequest.SetHeader("Cache-Control", "no-cache, must-revalidate")
			ChnJob.GetRequest.Timeout = DateTime.TicksPerSecond * 60
			ChnJob.Tag = strCMD
		Catch
			ChnJob.Release
			Fungsi.mpList.Put("Channel", "No Channel View")
			BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.NoGetList, Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
		End Try	
	End	If
End Sub

Sub GetListLiveTV
	Dim strCMD 	As String
	Dim ChnJob 	As MNCHttpJob
	
	strCMD = Fungsi.mpList.Get("URLListVOD")
	ChnJob.Initialize("GetListLiveTV", Me)

	Try
		ChnJob.Download(strCMD)
		ChnJob.GetRequest.SetHeader("Cache-Control", "no-cache, must-revalidate")
		ChnJob.GetRequest.Timeout = DateTime.TicksPerSecond * 60
		ChnJob.Tag = strCMD
	Catch
		ChnJob.Release
		Fungsi.mpList.Put("Channel", "No Channel View")
		BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.NoGetList, Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
	End Try	
End Sub

Sub GetListFavorite			
	Dim svtengah As ScrollView
	svtengah.Initialize(0)
	svtengah.Panel.RemoveAllViews
	pTengah.AddView(svtengah, 0, 1.5%y, pTengah.Width, pTengah.Height - 3%y)

	For i=0 To mlbFav.Size-1
		Dim PanEPG			As Panel
		Dim lTitle			As Label
		Dim ivThumbFav		As ImageView
		Dim ivPlay			As ImageView

		PanEPG.Initialize("")
		ivPlay.Initialize("")

		If Device = 0 Then
			PanEPG.LoadLayout("LiveTVDetSmart")
		Else
			PanEPG.LoadLayout("LiveTVDetail")
		End If

		ivEPGDet.Background = bmpTemp.Background
		ivEPGDet2.Background = bmpDelFav.Background
		lblEPGDet.TextColor = whitecolor
				
		
		svtengah.Panel.AddView(PanEPG, 0, (i*lblEPGDet.Height) + (i*1.5%y), svtengah.Width, lblEPGDet.Height)
		lblEPGDet.Text = mlbFav.GetValueAt(i)
		lblEPGDet.Tag =  mlbFav.GetKeyAt(i)
		lblEPGDet.Enabled = False
		ivEPGDet2.Tag = "Delete#None"
		
		If keyFav.ContainsKey(mlbFav.GetkeyAt(i)) Then
			ivThumDet.Bitmap = keyFav.GetBitmap(mlbFav.GetkeyAt(i))
			ivEPGDet.Tag = keyFav.GetSimple(mlbFav.GetkeyAt(i) & "#Label")
			ivThumDet.Tag = keyFav.GetSimple(mlbFav.GetkeyAt(i) & "#Label")
		End If
		svtengah.Panel.Height = (lblEPGDet.Height * mlbFav.Size) + (mlbFav.Size * 1.5%y) + pbMain.Height
	Next
	

	GifSmart.Visible = False
End Sub

Sub evPlayVideo_Click
	Dim ivTemp	As ImageView = Sender
	Dim ChnJob 	As MNCHttpJob
	Dim Pisah()	As String
	
	Pisah = Regex.Split("#", ivTemp.Tag)
	GifSmart.Visible = True
	ChnJob.Initialize("GetURLLiveTV", Me)

	Try
		ChnJob.Download(Pisah(0))
		ChnJob.GetRequest.SetHeader("Cache-Control", "no-cache, must-revalidate")
		ChnJob.GetRequest.Timeout = DateTime.TicksPerSecond * 60
		ChnJob.Tag = ivTemp.Tag
	Catch
		ChnJob.Release
		Fungsi.mpList.Put("Channel", "No Channel View")
		BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.NoGetList, Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
	End Try	
End Sub

Sub lblEPGDet_Click
	Dim lblTemp			As Label = Sender
	Dim strPisah()	As String
	Dim tmpView			As View
	
	tmpView = Fungsi.GetParent(lblTemp)
	
	If tmpView Is Panel Then
		Dim tmpPanel	As Panel = tmpView
		
		For Each z As View In tmpPanel.GetAllViewsRecursive
			If z Is ImageView Then
				Dim tmpImage 	As ImageView = z
				Dim Pisah()		As String
				
				Pisah = Regex.Split("#", tmpImage.Tag)
				If Pisah(1) = "Thumb" Then
					EPGList.BmpPre = tmpImage.Bitmap
					EPGList.Aktif = 1
					Exit
				End If
			End If
		Next

		strPisah = Regex.Split("#", lblTemp.Tag)
		EPGList.ColCode = strPisah(1)
		EPGList.ChaCode =	strPisah(0)		
		StartActivity("EPGList")	
	End If		
End Sub

Sub ivVOD_Click
	If Fungsi.DataConfig.sTab <> "VOD" Then pTengah.RemoveAllViews

	CallSub(ImageDownloader, "ActivityIsPaused")

	DoEvents	
	Fungsi.DataConfig.sTab = "VOD"
	Fungsi.tulisfileconfig(Fungsi.DataConfig)
	init_layout
	Cek_Koneksi	
End Sub

Sub GetListVOD
	Dim HomeJob As MNCHttpJob
	Dim mPage		As Map	
	Dim Gen			As JSONGenerator
	Dim	strEnc	As String

	mPage.Initialize
	mPage.Clear
	mPage.Put(Fungsi.mpList.Get("Variabel"), "new-release")
	
	Gen.Initialize(mPage)
	HomeJob.Initialize("GetListVOD", Me)

	Try
		HomeJob.Tag = "new-release"
		strEnc = Fungsi.ProsesEnkripsi(Gen.ToPrettyString(2))
		If strEnc = "Enkripsi Failed" Then Return
		HomeJob.PostString(Fungsi.mpList.Get("SlugURL"), "teks=" & strEnc)
		HomeJob.GetRequest.SetHeader("Cache-Control", "no-cache, must-revalidate")
		HomeJob.GetRequest.Timeout = DateTime.TicksPerSecond * 60
	Catch
		BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.CateFail, Fungsi.btnOK, "", "", Fungsi.GetDrawable(Fungsi.icWarning))
	End Try
End Sub

Sub lblGenre_Click
	Activity_KeyPress(4)
End Sub

Sub pgvEventVOD_ItemClick (Position As Int, Value As Object)
	Dim A As Object

	Content.A = Value
	Content.i = Position
	Content.gambar = picture.Get(Position)

	StartActivity("Content")
End Sub

Sub evFavorite_Click
	Dim ivTemp2		As ImageView = Sender
	Dim Fp			As Panel	 = Fungsi.GetParent(ivTemp2)

#Region ivtemp2.Tag Merah / Putih
	If ivTemp2.Tag  = "Putih#None" Then
		ivTemp2.Tag = "Merah#None"
		ivTemp2.Background = Fungsi.GetDrawable("ico_favoritered")
	Else If ivTemp2.Tag = "Merah#None" Then
		ivTemp2.Tag		= "Putih#None"
		ivTemp2.Background = Fungsi.GetDrawable("ico_favorite")
	End If
#End Region

 For Each v As View In  Fp.GetAllViewsRecursive
	If v Is Label Then
		Dim bcoba  As Label = v

		If ivTemp2.Tag = "Merah#None" Then
			mlbFav.Put(bcoba.tag, bcoba.text)
		Else If ivTemp2.Tag = "Putih#None" Then
			mlbFav.Remove(bcoba.tag)
		Else If ivTemp2.Tag = "Delete#None" Then
			mlbFav.Remove(bcoba.tag)
		End If
			
		Log(mlbFav.size)
	Else If v Is ImageView Then
		Dim ivcoba As ImageView = v
		Dim Pisah() As String
	
		Pisah = Regex.Split("#", ivcoba.Tag)
								
			If Pisah(1) = "Thumb" Then
					If ivTemp2.Tag = "Merah#None" Then
						keyFav.PutBitmap(Pisah(2) & "#" & Pisah(3), ivcoba.Bitmap)
						keyFav.PutSimple(Pisah(2) & "#" & Pisah(3) & "#Label", ivcoba.Tag)
						WriteMapFile
					Else If ivTemp2.Tag = "Putih#None" Then
						keyFav.Remove(Pisah(2) & "#" & Pisah(3))
						WriteMapFile
					Else If ivTemp2.Tag = "Delete#None" Then
							keyFav.Remove(Pisah(2) & "#" & Pisah(3))
							WriteMapFile
							DelItemFav
					End If
			End If
	End If
Next
End Sub

Sub DelItemFav
	pTengah.RemoveAllViews
	GetListFavorite
End Sub

Sub WriteMapFile
	File.WriteMap(File.DirDefaultExternal, Fungsi.DataConfig.Username & "_label", mlbFav)
End Sub
