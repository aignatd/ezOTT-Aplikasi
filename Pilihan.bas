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

	Private ivLiveTV 	As ImageView
	Private ivTVOD 		As ImageView
	Private BDUtama		As BetterDialogs	
	Private ivVOD 		As ImageView
	Private ivMetube 	As ImageView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:

	Activity.LoadLayout("Pilihan")

	ivLiveTV.Background = Fungsi.GetDrawable("pilihlivetv")
	ivTVOD.Background = Fungsi.GetDrawable("pilihtvod")
	ivVOD.Background = Fungsi.GetDrawable("pilihvod")
	ivMetube.Background = Fungsi.GetDrawable("pilihmetube")
End Sub

Sub Activity_Resume
	CallSub(ImageDownloader, "ActivityIsPaused")
	CallSub(EPGDownloader, "ActivityIsPaused")

	StopService(MNCUtils2Service)
	CancelScheduledService(MNCUtils2Service)
	StopService(ImageDownloader)
	CancelScheduledService(ImageDownloader)
	StopService(EPGDownloader)
	CancelScheduledService(EPGDownloader)
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub ivLiveTV_Click
	Fungsi.DataConfig.sTab = "LiveTV"
	Fungsi.tulisfileconfig(Fungsi.DataConfig)
	StartActivity(Utama)
	Activity.Finish
End Sub

Sub ivTVOD_Click
	Fungsi.DataConfig.sTab = "TVOD"
	Fungsi.tulisfileconfig(Fungsi.DataConfig)
	StartActivity(Utama)
	Activity.Finish
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'return true if you want to consume the event
	If KeyCode = 4 Then ' Back Key
		If BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.KeluarApps, Fungsi.btnOK, Fungsi.btnCancel, "", Fungsi.GetDrawable(Fungsi.icQuestion)) = DialogResponse.POSITIVE Then
			ExitApplication
		Else
			Return True
		End If
	End If
End Sub

Sub ivVOD_Click
	Fungsi.DataConfig.sTab = "VOD"
	Fungsi.tulisfileconfig(Fungsi.DataConfig)
	StartActivity(Utama)	
	Activity.Finish
End Sub

Sub ivMetube_Click
'	Dim p As PhoneIntents
	StartActivity(Webview)
'	StartActivity(p.OpenBrowser("http://metube.okezone.com/"))		

	Return
End Sub