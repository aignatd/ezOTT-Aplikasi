Type=StaticCode
Version=5.02
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
'Code module
'Subs in this code module will be accessible from all modules.
Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

	Type FormatConfig(aid As String, rsn As String, imei As String, _
	sTab As String, MacAddr As String, FolderDBS As String, _
	Username As String, Password As String, FolderMedia As String)
	
	Type FileData (Dir As String, FileName As String, KeyName As String, ContentType As String)

	Dim	mpList					As Map
	Dim	DirDBS					As String
	Dim	DirMedia				As String
	Dim DataConfig				As FormatConfig
	Dim raf						As RandomAccessFile

	Dim szAndroidID 			As String
	Dim szRefSerial 			As String
	Dim szIMEI					As String
	Dim szIMSI					As String
	Dim szManufacturer			As String
	Dim szModel					As String
	Dim	szProduct				As String
	Dim	szSubsID				As String

	Dim APIver 					As Int
	Dim refl 					As Reflector
	Dim myPhone 				As Phone
	Dim thisPhone 				As PhoneId
	Dim mKoneksi 				As Map
	Dim boundary 				As String
	
	#Region Messagebox
	Dim Asset					As String = "file:///android_asset/"	
	Dim TitleBox				As String = "OTT Playmedia"
	Dim btnOK					As String = "OK"
	Dim btnCancel				As String = "Cancel"
	Dim btnNO					As String = "NO"
	#End Region

	#Region Teks Message
	Dim InitFail			As String = "Initialize failed !"
	Dim LoadFail			As String = "Load init failed !"
	Dim NoKoneksi			As String = "Offline Connection or Server Down"
	Dim LoginFail			As String = "Login Failure !"
	Dim Unknown				As String = "Unknown Error"
	Dim ProsesFail			As String = "Process Failure !"
	Dim KeluarApps			As String = "Do you want to exit ?"
	Dim NoGetList			As String = "No Channel View or Server Down"
	Dim FailJSON			As String = "Initialize Failure !"
	Dim FailProses			As String = "Process Failure !"
	Dim NoMovie				As String = "Video not found"
	Dim NoEPGList			As String = "EPG list not found"
	Dim NoPlayer			As String = "There is no active player !"
	Dim EPGFail				As String = "EPG Failed !"
	Dim EPGEmpty			As String = "EPG Empty !"
	Dim EPGError			As String = "EPG Error !"
	Dim CateFail			As String = "Category Failure !"
	#End Region

	#Region Drawable Icon
	Dim icWarning			As String = "warning"
	Dim icQuestion			As String = "question"
	#Region
End Sub

Sub SetDirProg(FolderBaru As String)
	If File.ExternalWritable Then DirDBS = File.DirDefaultExternal Else DirDBS = File.DirInternal
	If File.ExternalWritable Then DirMedia = File.DirRootExternal & "/DCIM/Camera" Else DirMedia = File.DirInternal

	If (File.IsDirectory(DirDBS, FolderBaru) <> True) And (FolderBaru.Trim <> "") Then
		File.MakeDir(DirDBS, FolderBaru)
	End If

	DirDBS = DirDBS & "/" & FolderBaru & "/"
	mpList.Initialize
	mpList.Remove("DirDBS")
	mpList.Remove("DirMedia")
	mpList.Put("DirDBS", DirDBS)
	mpList.Put("DirMedia", DirMedia)
End Sub

Sub SetFileConfig As Boolean
	If File.Exists(DirDBS, "setting") = False Then
		Dim myWifi 		As ABWifi
		Dim strMAC		As String

		If myWifi.ABLoadWifi() = True Then
			Try
				strMAC = myWifi.ABGetCurrentWifiInfo().MacAddress
				strMAC = strMAC.Replace(":", "-")
			Catch
				strMAC = "00-00-00-00-00-00"
			End Try
  	Else
			strMAC = "00-00-00-00-00-00"
  	End If
	
		DataConfig.aid  				= szAndroidID
		DataConfig.rsn  				= szRefSerial
		DataConfig.imei 				= szIMEI
		DataConfig.Username	 		= "Guest"
		DataConfig.Password	 		= "None"
		DataConfig.MacAddr 			= strMAC
		DataConfig.sTab					= "LiveTV"
		DataConfig.FolderDBS		= DirDBS
		DataConfig.FolderMedia	= DirDBS
		Return TulisFileConfig(DataConfig)
	Else
		'File.Delete(DirDBS, "setting")
		Return True
	End If
End Sub

Sub TulisFileConfig(IsiConfig As FormatConfig) As Boolean	
	Dim mpTemp		As Map
	
	mpTemp.Initialize
	mpTemp.Clear
	mpTemp.Put("DataConfig", IsiConfig)
	
	Try
		File.Delete(DirDBS, "setting")
		raf.Initialize(DirDBS, "setting", False)
		raf.WriteEncryptedObject(mpTemp, szAndroidID & szRefSerial & szIMEI, raf.CurrentPosition)
		raf.Close
	Catch
		Msgbox2("Init data failed", "ezSchool", "OK", "", "", LoadBitmap(File.DirAssets, "Warning.png"))
		Return False
	End Try
	
	Return True
End Sub

Sub InfoDevice	
	'First Determine the version
	APIver = refl.GetStaticField("android.os.Build$VERSION", "SDK_INT")
	If APIver < 9 Then
		szRefSerial = "n/a"
	Else
		szRefSerial = refl.GetStaticField("android.os.Build", "SERIAL")
	End If
	
	szAndroidID = myPhone.GetSettings("android_id")
	szManufacturer = myPhone.Manufacturer
	szModel = myPhone.Model
	szProduct = myPhone.Product
	szIMEI = thisPhone.GetDeviceId
	szIMSI = thisPhone.GetSimSerialNumber
	szSubsID = thisPhone.GetSubscriberId
End Sub

Sub BacaFileConfig As Boolean
	Dim mpTemp	As Map
		
	mpTemp.Initialize
	mpTemp.Clear
	
	InfoDevice

	If File.Exists(DirDBS, "setting") = False Then
		If SetFileConfig = False Then ExitApplication
	End If	

	Try
		raf.Initialize(DirDBS, "setting", False)
		mpTemp = raf.ReadEncryptedObject(szAndroidID & szRefSerial & szIMEI, raf.CurrentPosition)
		raf.Flush
		raf.Close
	Catch
		Msgbox2("Invalid Application !", "ezSchool", "OK", "", "", LoadBitmap(File.DirAssets, "Warning.png"))
		If File.Exists(DirDBS, "setting") Then File.Delete(DirDBS, "setting")
		Return False
	End Try

	DataConfig = mpTemp.Get("DataConfig")	
	Return True
End Sub

Sub DisplaySize(SizeOct As Double) As String
	Dim txtUnits(4) As String
	txtUnits = Array As String("bytes", "Kb", "Mb", "Gb")
	Dim Unité As Int
	Unité = 0
	Do While SizeOct > 1024
		Unité = Unité + 1
		SizeOct = SizeOct / 1024
	Loop
	If SizeOct <> Floor(SizeOct) Then
		Return NumberFormat(SizeOct, 1, 1) & " " & txtUnits(Unité)
	Else
		Return SizeOct & " " & txtUnits(Unité)
	End If
End Sub

Sub GetParent(MyView As View) As View
	Dim r As Reflector

	r.Target = MyView
	Return r.RunMethod("getParent")
End Sub

Sub GetDrawable(Name As String) As Object
	 Dim r As Reflector
	 Dim package As String
	 Dim id As Int
	 package = r.GetStaticField("anywheresoftware.b4a.BA", "packageName")
	 id = r.GetStaticField(package & ".R$drawable", Name)
	 r.Target = r.GetContext
	 r.Target = r.RunMethod("getResources")
	 Return r.RunMethod2("getDrawable", id, "java.lang.int")
End Sub

Sub CreateStateListDrawable(bmp As Bitmap,cmd As View )
  Dim C As Canvas
  Dim r1 As Rect
  Dim r2 As Rect
  Dim sld As StateListDrawable
  Dim Padding As Int
  Padding = 0
  'Create a temporary button and draw on it
  C.Initialize(cmd)
  r1.Initialize(0,0,cmd.Width,cmd.Height)
  r2.Initialize(Padding,Padding,cmd.Width-Padding,cmd.height-Padding)
  sld.Initialize

  C.DrawColor(Colors.RGB(58, 162, 203))
  C.DrawBitmap(bmp,Null,r2)
  cmd.Invalidate
  sld.AddState(sld.State_Pressed,cmd.Background)

  Dim bmd As BitmapDrawable
  bmd.Initialize(bmp)
  sld.AddState(sld.State_Enabled, bmd)

  cmd.Background = sld
End Sub

Sub CreateScaledBitmap(Original As Bitmap, Width As Int, Height As Int) As Bitmap
	Dim r As Reflector
	Dim b As Bitmap
	b = r.RunStaticMethod("android.graphics.Bitmap", "createScaledBitmap", _
			Array As Object(Original, Width, Height, True), _
			Array As String("android.graphics.Bitmap", "java.lang.int", "java.lang.int", "java.lang.boolean"))
	Return b
End Sub

Sub GetFullName(FullPath As String) As String
	Return FullPath.SubString(FullPath.LastIndexOf("/")+1)
End Sub

Sub GetFileName(FullPath As String) As String
	Dim strTemp As String
  strTemp = GetFullName(FullPath)
	Return strTemp.SubString2(0, strTemp.LastIndexOf("."))
End Sub

Sub GetFileExt(FullPath As String) As String
  Return FullPath.SubString(FullPath.LastIndexOf(".")+1)
End Sub

Sub GetParentPath(Path As String) As String
	Dim Path1 As String 
	Dim L 		 As Int
	If Path = "/" Then
	  Return "/"
	End If
	L = Path.LastIndexOf("/")
	If L = Path.Length - 1 Then
	  'Strip the last slash
	  Path1 = Path.SubString2(0,L)
	Else
	  Path1 = Path
	End If
	L = Path.LastIndexOf("/")
	If L = 0 Then
	  L = 1
	End If
	Return Path1.SubString2(0,L)
End Sub

Public Sub AdjustBitmap(bmp As Bitmap, Width As Int, Height As Int) As Bitmap
	Dim RatioBmp, RatioIV As Float
	RatioBmp = bmp.Width / bmp.Height
	RatioIV = Width / Height
	Dim Diviseur As Float
	If RatioIV > RatioBmp Then
		Diviseur = bmp.Height / Height
		bmp = CreateScaledBitmap(bmp, Round(bmp.Width / Diviseur / Density), Round(Height / Density))
	Else
		Diviseur = bmp.Width / Width
		bmp = CreateScaledBitmap(bmp, Round(Width / Density), Round(bmp.Height / Diviseur / Density))
	End If
	Return bmp
End Sub

Sub AdjustImageView(Imv As ImageView, Img As Bitmap)
  Private bmp As Bitmap = Img

  Dim Delta, Height, Width As Int
  If bmp.Width / bmp.Height > Imv.Width / Imv.Height Then
      Height = bmp.Height / bmp.Width * Imv.Width
      Delta = (Imv.Height - Height) / 2
      Imv.Height = Height
      Imv.Top = Imv.Top + Delta
  Else
      Width = bmp.Width / bmp.Height * Imv.Height
      Delta = (Imv.Width - Width) / 2
      Imv.Width = Width
      Imv.Left = Imv.Left + Delta
  End If
  Imv.Gravity = Gravity.FILL
  Imv.Bitmap = bmp
End Sub

Sub ProsesEnkripsi(Data As String) As String
	Dim peKey 		As String
	Dim pestrEnc 	As String
	Dim pesutils 	As StringUtils

	Try
		peKey = Keygen
		pestrEnc = Wrap(Data, peKey)
		pestrEnc = pesutils.EncodeBase64(pestrEnc.GetBytes("UTF8"))
'		pestrEnc = pesutils.EncodeUrl(pestrEnc, "UTF8")
	Catch
		Msgbox2("Process Failed !", "IKR Playbox", "OK", "", "", LoadBitmap(File.DirAssets, "warning.png"))			
		Return "Enkripsi Failed"
	End Try
	
	Return pestrEnc
End Sub

Sub Keygen() As String
	Dim sr As SecureRandom
	Dim gkey, lCode, rHead, charArray As String
	Dim i, length As Int
	
	charArray = "zyxwvutsrqponmlkjihgfedcba0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	
	length = Rnd(10, 15)
	lCode = Bit.ToHexString(length)

	For i = 0 To 2
		rHead = rHead&charArray.CharAt(Rnd(0,61))
	Next

	For i = 0 To length-1
		gkey = gkey&charArray.CharAt(Rnd(0,61))
	Next

	gkey = rHead&lCode&gkey
	
	Return gkey
End Sub

Sub Wrap(Text As String, vkey As String) As String
	Dim trueKey, encrypted, wrapped As String
	Dim len As Int
	
	len = Bit.ParseInt(vkey.CharAt(3),16)
	trueKey = vkey.SubString2(4,4+len)
	encrypted = EnkripDekrip("AES", trueKey, "MD5", Text, "Encode")
	wrapped = vkey & encrypted

	Return wrapped
End Sub

Sub EnkripDekrip(JenisEnkripsi As String, KataKunci As String, JenisHash As String, IsiData As String, PilihMetode As String) As String
	Dim kg 					As KeyGenerator
	Dim md 					As MessageDigest
	Dim C 					As Cipher
	Dim hasilnya() 			As Byte
	Dim	bc					As ByteConverter
  	Dim su 					As StringUtils
	
	kg.Initialize(JenisEnkripsi)
	kg.KeyFromBytes(md.GetMessageDigest(KataKunci.GetBytes("UTF8"), JenisHash))
	C.Initialize(JenisEnkripsi)
	
	Try
		If PilihMetode = "Enkripsi" Or PilihMetode = "Encode" Then
			hasilnya = C.Encrypt(IsiData.GetBytes("UTF8"), kg.Key, False)
		Else
		If PilihMetode = "Decode" Then
			hasilnya = su.DecodeBase64(IsiData)
			hasilnya = C.Decrypt(hasilnya, kg.Key, False)
		Else
			hasilnya = bc.HexToBytes(IsiData)
			hasilnya = C.Decrypt(hasilnya, kg.Key, False)
		End If
		End If
	Catch
		ProgressDialogHide
		DoEvents
		Return False
	End Try
	
	If PilihMetode = "Enkripsi" Then
		Return bc.HexFromBytes(hasilnya)
	Else
	If PilihMetode = "Encode" Then
		Return su.EncodeBase64(hasilnya)
	Else
		Return bc.StringFromBytes(hasilnya, "UTF8")
	End If
	End If
End Sub

Sub CacahJSON(DataJSON As String, Status As String) As Boolean
	Dim parser 	As JSONParser	
	
	Try	
		parser.Initialize(DataJSON)
	Catch
		Msgbox2("Process Failure !", "IKR Playbox", "OK", "", "", LoadBitmap(File.DirAssets, "warning.png"))			
		Return False
	End Try
	
	Select Status
		Case "NextArray"
			Try
				Dim root As List = parser.NextArray
			Catch
				Return False
			End Try
			
			For Each colroot As Map In root
 				Dim IPAddr0 As String = colroot.Get("IPAddr0") 
 				Dim IPAddr1 As String = colroot.Get("IPAddr1") 
 				Dim IPAddr2 As String = colroot.Get("IPAddr2") 
 				Dim IPAddr3 As String = colroot.Get("IPAddr3") 
 				Dim IPAddr4 As String = colroot.Get("IPAddr4") 
 				Dim Port0		As String = colroot.Get("Port0")
 				Dim Port1		As String = colroot.Get("Port1")
		
				Dim RegisterList As List = colroot.Get("Register") 
				For Each colRegister As Map In RegisterList
				mpList.Put("RegisterURL", colRegister.Get("RegisterURL"))
   				mpList.Put("firstname", colRegister.Get("firstname"))
   				mpList.Put("lastname", colRegister.Get("lastname"))
   				mpList.Put("regemail", colRegister.Get("regemail"))
   				mpList.Put("handphone", colRegister.Get("handphone"))
   				mpList.Put("regpass", colRegister.Get("regpass"))
   				mpList.Put("gender", colRegister.Get("gender"))
					mpList.Put("homephone", colRegister.Get("homephone"))
					mpList.Put("id_city", colRegister.Get("id_city"))
					mpList.Put("zipcode", colRegister.Get("zipcode"))
				Next
				
				Dim LoginList As List = colroot.Get("Login") 
				For Each colLogin As Map In LoginList
					mpList.Put("LoginURL", colLogin.Get("LoginURL"))
   				mpList.Put("username", colLogin.Get("username"))
					mpList.Put("loginpass", colLogin.Get("loginpass"))
				Next

				Dim ListVOD As List = colroot.Get("GetListVOD") 
				For Each colListVOD As Map In ListVOD
					mpList.Put("URLListVOD", colListVOD.Get("URLListVOD"))
					mpList.Put("PicListVOD", colListVOD.Get("PicListVOD"))
					mpList.Put("PicVOD", colListVOD.Get("PicVOD"))
				Next
		
				Dim ListEPG As List = colroot.Get("GetListEPG") 
				For Each colListEPG As Map In ListEPG
					mpList.Put("URLListEPG", colListEPG.Get("URLListEPG"))
					mpList.Put("channelcode", colListEPG.Get("channelcode"))
					mpList.Put("columncode", colListEPG.Get("columncode"))
				Next

				Dim MovieEPG As List = colroot.Get("GetMovieEPG") 
				For Each colMovieEPG As Map In MovieEPG
					mpList.Put("URLMovieEPG", colMovieEPG.Get("URLMovieEPG"))
					mpList.Put("chancode", colMovieEPG.Get("channelcode"))
					mpList.Put("pcode", colMovieEPG.Get("pcode"))
				Next

				Dim LiveTV As List = colroot.Get("GetURLLiveTV") 
				For Each colLiveTV As Map In LiveTV
					mpList.Put("URLLiveTV", colLiveTV.Get("URLLiveTV"))
					mpList.Put("LiveTVchancode", colLiveTV.Get("channelcode"))
					mpList.Put("LiveTVcolcode", colLiveTV.Get("columncode"))
				Next

				Dim LiveTVEPG As List = colroot.Get("GetLiveTVEPG") 
				For Each colLiveTVEPG As Map In LiveTVEPG
					mpList.Put("URLLiveTVEPG", colLiveTVEPG.Get("URLLiveTVEPG"))
					mpList.Put("EPGchancode", colLiveTVEPG.Get("channelcode"))
					mpList.Put("EPGcolcode", colLiveTVEPG.Get("columncode"))
				Next

				Dim LiveVOD As List = colroot.Get("SlugPager") 
				For Each colLiveVOD As Map In LiveVOD
					mpList.Put("SlugURL", colLiveVOD.Get("SlugURL"))
					mpList.Put("Variabel", colLiveVOD.Get("Variabel"))
				Next

				Dim EPGHour As List = colroot.Get("GetEPGHour") 
				For Each colEPGHour As Map In EPGHour
					mpList.Put("EPGHour", colEPGHour.Get("EPGHour"))
					mpList.Put("Hourchancode", colEPGHour.Get("channelcode"))
					mpList.Put("Hourcolcode", colEPGHour.Get("columncode"))
				Next
			Next
			
			mKoneksi = colroot
	End Select

	Return True
End Sub

Sub UnWrap(Text As String) As String
	Dim truekey, unwrapped As String
	Dim len As Int
	
	len = Bit.ParseInt(Text.CharAt(3),16)
	truekey = Text.SubString2(4,4+len)
	unwrapped = EnkripDekrip("AES", truekey, "MD5", Text.SubString(4+len), "Decode")
	
	Return unwrapped
End Sub

Sub CreatePostRequest(NameValues As Map, Files As List) As OutputStream
	boundary = "---------------------------1461124740692"
	Dim stream As OutputStream
	stream.InitializeToBytesArray(20)
	Dim EOL As String
	EOL = Chr(13) & Chr(10) 'CRLF constant matches Android end of line character which is chr(10).
	Dim b() As Byte
	If NameValues <> Null And NameValues.IsInitialized Then
		'Write the name/value pairs
		Dim key, value As String
		For i = 0 To NameValues.Size - 1
			key = NameValues.GetKeyAt(i)
			value = NameValues.GetValueAt(i)
			b = ("--" & boundary & EOL & "Content-Disposition: form-data; name=" _ 
				& QUOTE & key & QUOTE & EOL & EOL & value & EOL).GetBytes("UTF8")
			stream.WriteBytes(b, 0, b.Length)
		Next
	End If
	If Files <> Null And Files.IsInitialized Then
		'write the files
		Dim FD As FileData
		For i = 0 To Files.Size - 1
			FD = Files.Get(i)
			b = ("--" & boundary & EOL & "Content-Disposition: form-data; name=" _ 
				& QUOTE & FD.KeyName & QUOTE & "; filename=" & QUOTE & FD.FileName & QUOTE _
				& EOL & "Content-Type: "  & FD.ContentType & EOL & EOL).GetBytes("UTF8")
			stream.WriteBytes(b, 0, b.Length)
			Dim In As InputStream
			In = File.OpenInput(FD.Dir, FD.FileName)
			File.Copy2(In, stream) 'read the file and write it to the stream
			b = EOL.GetBytes("UTF8")
			stream.WriteBytes(b, 0, b.Length)
		Next
	End If
	b = (EOL & "--" & boundary & "--" & EOL).GetBytes("UTF8")
	stream.WriteBytes(b, 0, b.Length)
	Return stream
End Sub

Sub SetDivider(lv As ListView, Color As Int, Height As Int)
   Dim r As Reflector
   r.Target = lv
   Dim CD As ColorDrawable
   CD.Initialize(Color, 0)
   r.RunMethod4("setDivider", Array As Object(CD), Array As String("android.graphics.drawable.Drawable"))
   r.RunMethod2("setDividerHeight", Height, "java.lang.int")
End Sub