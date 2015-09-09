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
	Dim tmrLoad As Timer
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

'	Private wvOrder As WebView
	Private ivBack As ImageView
	Private ivHome As ImageView
	Private wvMeTube As WebView
	Dim wvXtender As WebViewXtender
	Dim lblProgress As Label
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	'Activity.LoadLayout("Layout1")
	
	Activity.LoadLayout("metube_webview")
	Activity.Color = Colors.ARGB(0, 255, 0, 0)
	
	tmrLoad.Initialize("tmrLoad", 200) ' 1000 = 1 second
	tmrLoad.Enabled = True
	wvMeTube.LoadURL("http://metube.okezone.com/")
	wvMeTube.ZoomEnabled = False	
End Sub

Sub Activity_Resume

End Sub
Sub tmrLoad_Tick
   'Handle tick events
   DoEvents
   lblProgress.Text = wvXtender.getProgress(wvMeTube) & "%"
   DoEvents
   If lblProgress.Text = "100%" Then
   	tmrLoad.Enabled = False
	lblProgress.Visible= False
   End If
End Sub
Sub Activity_Pause (UserClosed As Boolean)

End Sub
Sub ivHome_Click
	
End Sub
Sub ivBack_Click
	
End Sub