Type=Service
Version=5.02
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Service Attributes 
	#StartAtBoot: False
#End Region

Sub Process_Globals
	Private cachex As Map
	Private tasksx As Map
	Private ongoingTasksx As Map
End Sub

Sub Service_Create
	tasksx.Initialize
	cachex.Initialize
	ongoingTasksx.Initialize
End Sub

Sub Service_Start (StartingIntent As Intent)

End Sub

Sub Service_Destroy

End Sub

Sub Download (ImageViewsMap As Map)
	For i = 0 To ImageViewsMap.Size - 1
'		tasksx.Put(ImageViewsMap.GetKeyAt(i), ImageViewsMap.GetValueAt(i))
		Dim link As String = ImageViewsMap.GetValueAt(i)

		Dim j As HttpJob
		j.Initialize("AmbilEPG", Me)
		j.Download(link)
		j.Tag = ImageViewsMap.GetKeyAt(i)
		j.GetRequest.SetHeader("Cache-Control", "no-cache, must-revalidate")
		j.GetRequest.Timeout = DateTime.TicksPerSecond * 120

'		If cachex.ContainsKey(link) Then
'			Dim iv As Label = ImageViewsMap.GetKeyAt(i)
'			iv.Text = cachex.Get(link)
'		Else If ongoingTasksx.ContainsKey(link) = False Then
'			ongoingTasksx.Put(link, "")
'			Dim j As HttpJob
'			j.Initialize(link, Me)
'			j.Download(link)
'		End If
	Next
End Sub

Sub JobDone(Job As HttpJob)
'	ongoingTasksx.Remove(Job.JobName)
	If Job.Success Then
		Dim iv As Label = Job.Tag

		CacahEPG(iv, Job.GetString)		
		
'		Dim bmp As String = Job.GetString
'		cachex.Put(Job.JobName, bmp)
'		If tasksx.IsInitialized Then
'			For i = 0 To tasksx.Size - 1
'				Dim link As String = tasksx.GetValueAt(i)
'				If link = Job.JobName Then
'					Dim iv As Label = tasksx.GetKeyAt(i)
'					
'					CacahEPG(iv, bmp)
'				End If
'			Next
'		End If
	Else
	End If
	Job.Release
End Sub

Sub ActivityIsPaused
	tasksx.Clear
End Sub

Sub CacahEPG(Viewer As Label, IsiLabel As String)
	Dim sf				As StringFunctions
	Dim parser 		As JSONParser	
	Dim mresult		As Map
	Dim ListEPG		As List
	Dim ListDesc	As List
	Dim lstPrev		As List
	
	For i=0 To 1
		Dim waktu		As List
		Dim rsFont	As RichString
		Dim Teks1		As String
		Dim Teks2		As String
		Dim Waktu1	As String
		Dim Waktu2	As String

		Try
			parser.Initialize(IsiLabel)
		Catch
			Viewer.Text = Fungsi.EPGFail
			Return
		End Try
		
		Try
			mresult.Initialize
			mresult = parser.NextObject
		Catch
			Viewer.Text = Fungsi.EPGEmpty
			Return
		End Try

		If mresult.Get("returncode") <> "0" Then
			Viewer.Text = Fungsi.EPGError
		Else
			ListEPG 	= mresult.Get("begintime")
			ListDesc 	= mresult.Get("programdescription")
			lstPrev 	= mresult.Get("prevuecode")
			
			For i=(ListEPG.Size-2) To (ListEPG.Size-1)
				waktu = sf.Split(ListEPG.Get(i), " ")
				
				If i = ListEPG.Size-2 Then
					Waktu1 	= waktu.Get(1)
					Teks1		= ListDesc.Get(i)
				Else
					Waktu2 	= waktu.Get(1)
					Teks2		= ListDesc.Get(i)
				End If
			Next
			
			rsFont.Initialize("{Putih}{Tebal}" & Waktu1 & " | " & Teks1 & "{Tebal}{Putih}" & CRLF & Waktu2 & " | " & Teks2)
			rsFont.Color2(Colors.White, "{Putih}")
			rsFont.Style2(rsFont.STYLE_BOLD, "{Tebal}")
			Viewer.Text = rsFont
		End If	
	Next
End Sub

































