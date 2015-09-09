package ottplaymedia.mncplaymedia.com;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isFirst) {
			processBA = new BA(this.getApplicationContext(), null, null, "ottplaymedia.mncplaymedia.com", "ottplaymedia.mncplaymedia.com.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
				p.finish();
			}
		}
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		mostCurrent = this;
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
		BA.handler.postDelayed(new WaitForLayout(), 5);

	}
	private static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "ottplaymedia.mncplaymedia.com", "ottplaymedia.mncplaymedia.com.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "ottplaymedia.mncplaymedia.com.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEvent(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return main.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null) //workaround for emulator bug (Issue 2423)
            return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
			if (mostCurrent == null || mostCurrent != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
		    processBA.raiseEvent(mostCurrent._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}

public anywheresoftware.b4a.keywords.Common __c = null;
public adr.splashfadelibrary.splashfade _vvvvvvvvvvvvvvvvvvvvvvv4 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _vvvvvvvvvvvvvvvvvvvvvvv6 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _iv1 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etemail = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _iv2 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etpass = null;
public anywheresoftware.b4a.objects.ButtonWrapper _bsubmit = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _iv3 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _iv4 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _vvvvvvvvvvvvvvvvvvvvvvv7 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _vvvvvvvvvvvvvvvvvvvvvvv0 = null;
public anywheresoftware.b4a.objects.LabelWrapper _vvvvvvvvvvvvvvvvvvvvvvvv1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _vvvvvvvvvvvvvvvvvvvvvvvv2 = null;
public anywheresoftware.b4a.objects.collections.Map _vvvvvvvvv0 = null;
public com.datasteam.b4a.xtraviews.MovieViewControl _gifmain = null;
public anywheresoftware.b4a.objects.ButtonWrapper _vvvvvvvvvvvvvvvvvvvvvvvv3 = null;
public flm.b4a.betterdialogs.BetterDialogs _vvvvvvvvvvvvvvvvvvvvvvv5 = null;
public anywheresoftware.b4a.objects.IntentWrapper _vvvvvvvvvvvvvvvvvvvvvvvv4 = null;
public static int _vvvvvvvvvvvvvvvvvvvvvvvv5 = 0;
public anywheresoftware.b4a.phone.PackageManagerWrapper _vvvvvvvvvvvvvvvvvvvvvvvv6 = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _vvvvvvvvvvvvvvv5 = null;
public b4a.example.dateutils _vvvvvvvvvvvvvvv6 = null;
public ottplaymedia.mncplaymedia.com.fungsi _vvvvvvvvvvvvvvv0 = null;
public ottplaymedia.mncplaymedia.com.utama _vvvvvvvvvvvvvvvv1 = null;
public ottplaymedia.mncplaymedia.com.epglist _vvvvvvvvvvvvvvvv2 = null;
public ottplaymedia.mncplaymedia.com.register _vvvvvvvvvvvvvvvv3 = null;
public ottplaymedia.mncplaymedia.com.pilihan _vvvvvvvvvvvvvvvv4 = null;
public ottplaymedia.mncplaymedia.com.epgdownloader _vvvvvvvvvvvvvvvv5 = null;
public ottplaymedia.mncplaymedia.com.imagedownloader _vvvvvvvvvvvvvvvv6 = null;
public ottplaymedia.mncplaymedia.com.content _vvvvvvvvvvvvvvvv7 = null;
public ottplaymedia.mncplaymedia.com.webview _vvvvvvvvvvvvvvvv0 = null;
public ottplaymedia.mncplaymedia.com.mncutils2service _vvvvvvvvvvvvvvvvv1 = null;

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
vis = vis | (utama.mostCurrent != null);
vis = vis | (epglist.mostCurrent != null);
vis = vis | (register.mostCurrent != null);
vis = vis | (pilihan.mostCurrent != null);
vis = vis | (content.mostCurrent != null);
vis = vis | (webview.mostCurrent != null);
return vis;}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 62;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 67;BA.debugLine="Activity.Color = Colors.ARGB(255, 50, 50, 50)";
mostCurrent._activity.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (50),(int) (50),(int) (50)));
 //BA.debugLineNum = 70;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 71;BA.debugLine="Fungsi.SetDirProg(\"\")";
mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvvvv1(mostCurrent.activityBA,"");
 //BA.debugLineNum = 72;BA.debugLine="Fungsi.BacaFileConfig";
mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv1(mostCurrent.activityBA);
 //BA.debugLineNum = 73;BA.debugLine="Fungsi.mpList.Put(\"DirDBS\", Fungsi.DirDBS)";
mostCurrent._vvvvvvvvvvvvvvv0._v5.Put((Object)("DirDBS"),(Object)(mostCurrent._vvvvvvvvvvvvvvv0._v6));
 };
 //BA.debugLineNum = 78;BA.debugLine="If GetDeviceLayoutValues.ApproximateScreenSize <";
if (anywheresoftware.b4a.keywords.Common.GetDeviceLayoutValues(mostCurrent.activityBA).getApproximateScreenSize()<5) { 
 //BA.debugLineNum = 80;BA.debugLine="If Activity.Width > Activity.Height Then";
if (mostCurrent._activity.getWidth()>mostCurrent._activity.getHeight()) { 
 //BA.debugLineNum = 81;BA.debugLine="sfMain.Initialize(Activity, Me, \"splashlandscap";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvv4._initialize(mostCurrent.activityBA,mostCurrent._activity,main.getObject(),"splashlandscape.jpg",anywheresoftware.b4a.keywords.Common.Gravity.CENTER,(int) (2000),(int) (100),"Down",anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 83;BA.debugLine="sfMain.Initialize(Activity, Me, \"splashpotrait.";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvv4._initialize(mostCurrent.activityBA,mostCurrent._activity,main.getObject(),"splashpotrait.jpg",anywheresoftware.b4a.keywords.Common.Gravity.CENTER,(int) (2000),(int) (100),"Down",anywheresoftware.b4a.keywords.Common.True);
 };
 }else {
 //BA.debugLineNum = 87;BA.debugLine="If Activity.Width > Activity.Height Then";
if (mostCurrent._activity.getWidth()>mostCurrent._activity.getHeight()) { 
 //BA.debugLineNum = 88;BA.debugLine="sfMain.Initialize(Activity, Me, \"splashlandscap";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvv4._initialize(mostCurrent.activityBA,mostCurrent._activity,main.getObject(),"splashlandscape.jpg",anywheresoftware.b4a.keywords.Common.Gravity.CENTER,(int) (2000),(int) (100),"Down",anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 90;BA.debugLine="sfMain.Initialize(Activity, Me, \"splashpotrait.";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvv4._initialize(mostCurrent.activityBA,mostCurrent._activity,main.getObject(),"splashpotrait.jpg",anywheresoftware.b4a.keywords.Common.Gravity.CENTER,(int) (2000),(int) (100),"Down",anywheresoftware.b4a.keywords.Common.True);
 };
 };
 //BA.debugLineNum = 94;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 101;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 102;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 96;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 98;BA.debugLine="Fungsi.DirDBS = Fungsi.mpList.Get(\"DirDBS\")";
mostCurrent._vvvvvvvvvvvvvvv0._v6 = BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("DirDBS")));
 //BA.debugLineNum = 99;BA.debugLine="End Sub";
return "";
}
public static String  _breg_click() throws Exception{
 //BA.debugLineNum = 190;BA.debugLine="Sub bReg_Click";
 //BA.debugLineNum = 191;BA.debugLine="StartActivity(Register)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv3.getObject()));
 //BA.debugLineNum = 192;BA.debugLine="End Sub";
return "";
}
public static String  _bsubmit_click() throws Exception{
ottplaymedia.mncplaymedia.com.mnchttpjob _homejob = null;
String _strenc = "";
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _gen = null;
anywheresoftware.b4a.objects.collections.Map _mlogin = null;
 //BA.debugLineNum = 153;BA.debugLine="Sub bSubmit_Click";
 //BA.debugLineNum = 154;BA.debugLine="Dim HomeJob As MNCHttpJob";
_homejob = new ottplaymedia.mncplaymedia.com.mnchttpjob();
 //BA.debugLineNum = 155;BA.debugLine="Dim	strEnc	As String";
_strenc = "";
 //BA.debugLineNum = 156;BA.debugLine="Dim Gen			As JSONGenerator";
_gen = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();
 //BA.debugLineNum = 157;BA.debugLine="Dim mlogin	As Map";
_mlogin = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 159;BA.debugLine="GifMain.Visible = True";
mostCurrent._gifmain.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 160;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 //BA.debugLineNum = 161;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 //BA.debugLineNum = 163;BA.debugLine="StopService(MNCUtils2Service)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvvv1.getObject()));
 //BA.debugLineNum = 164;BA.debugLine="CancelScheduledService(MNCUtils2Service)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvvv1.getObject()));
 //BA.debugLineNum = 165;BA.debugLine="StopService(ImageDownloader)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()));
 //BA.debugLineNum = 166;BA.debugLine="CancelScheduledService(ImageDownloader)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()));
 //BA.debugLineNum = 167;BA.debugLine="StopService(EPGDownloader)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()));
 //BA.debugLineNum = 168;BA.debugLine="CancelScheduledService(EPGDownloader)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()));
 //BA.debugLineNum = 170;BA.debugLine="mlogin.Initialize";
_mlogin.Initialize();
 //BA.debugLineNum = 171;BA.debugLine="mlogin.Clear";
_mlogin.Clear();
 //BA.debugLineNum = 173;BA.debugLine="mlogin.Put(Fungsi.mpList.Get(\"username\"), etEmail";
_mlogin.Put(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("username")),(Object)(mostCurrent._etemail.getText()));
 //BA.debugLineNum = 174;BA.debugLine="mlogin.Put(Fungsi.mpList.Get(\"loginpass\"), etPass";
_mlogin.Put(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("loginpass")),(Object)(mostCurrent._etpass.getText()));
 //BA.debugLineNum = 175;BA.debugLine="Gen.Initialize(mlogin)";
_gen.Initialize(_mlogin);
 //BA.debugLineNum = 176;BA.debugLine="HomeJob.Initialize(\"Login\", Me)";
_homejob._initialize(processBA,"Login",main.getObject());
 //BA.debugLineNum = 178;BA.debugLine="Try";
try { //BA.debugLineNum = 179;BA.debugLine="HomeJob.Tag = \"Login\"";
_homejob._vvvvvvvvvvvvvvvvvvvvvvv1 = (Object)("Login");
 //BA.debugLineNum = 180;BA.debugLine="strEnc = Fungsi.ProsesEnkripsi(Gen.ToPrettyStrin";
_strenc = mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvvv0(mostCurrent.activityBA,_gen.ToPrettyString((int) (4)));
 //BA.debugLineNum = 181;BA.debugLine="If strEnc = \"Enkripsi Failed\" Then Return";
if ((_strenc).equals("Enkripsi Failed")) { 
if (true) return "";};
 //BA.debugLineNum = 182;BA.debugLine="HomeJob.PostString(Fungsi.mpList.Get (\"LoginURL\"";
_homejob._vvvvvvvvvvvvvvvvvvvvv7(BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("LoginURL"))),"teks="+_strenc);
 //BA.debugLineNum = 183;BA.debugLine="HomeJob.GetRequest.SetHeader(\"Cache-Control\", \"n";
_homejob._vvvvvvvvvvvvvvvvvvvvv1().SetHeader("Cache-Control","no-cache, must-revalidate");
 //BA.debugLineNum = 184;BA.debugLine="HomeJob.GetRequest.Timeout = DateTime.TicksPerSe";
_homejob._vvvvvvvvvvvvvvvvvvvvv1().setTimeout((int) (anywheresoftware.b4a.keywords.Common.DateTime.TicksPerSecond*60));
 } 
       catch (Exception e112) {
			processBA.setLastException(e112); //BA.debugLineNum = 186;BA.debugLine="BDLogin.Msgbox(Fungsi.TitleBox, Fungsi.LoadFail,";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvv5.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvv5,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 188;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 29;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 33;BA.debugLine="Private sfMain			As SplashFade";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvv4 = new adr.splashfadelibrary.splashfade();
 //BA.debugLineNum = 35;BA.debugLine="Private ivLogin 		As ImageView";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvv6 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private iv1 			As ImageView";
mostCurrent._iv1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private etEmail 		As EditText";
mostCurrent._etemail = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Private iv2 			As ImageView";
mostCurrent._iv2 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private etPass 			As EditText";
mostCurrent._etpass = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private bSubmit 		As Button";
mostCurrent._bsubmit = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private iv3 			As ImageView";
mostCurrent._iv3 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private iv4 			As ImageView";
mostCurrent._iv4 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Private ivBg			As ImageView";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvv7 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Private ivUser 			As ImageView";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvv0 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Private lblUser 		As Label";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Private lblNamaFilm 	As Label";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 47;BA.debugLine="Private user 			As Map";
mostCurrent._vvvvvvvvv0 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 48;BA.debugLine="Private GifMain			As MovieViewControl";
mostCurrent._gifmain = new com.datasteam.b4a.xtraviews.MovieViewControl();
 //BA.debugLineNum = 49;BA.debugLine="Private RCTI 			As Button";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv3 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 51;BA.debugLine="Private BDLogin			As BetterDialogs";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvv5 = new flm.b4a.betterdialogs.BetterDialogs();
 //BA.debugLineNum = 54;BA.debugLine="Private ups 			As Intent";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv4 = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 55;BA.debugLine="Private ipack			As Int";
_vvvvvvvvvvvvvvvvvvvvvvvv5 = 0;
 //BA.debugLineNum = 56;BA.debugLine="Private pm 				As PackageManager";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv6 = new anywheresoftware.b4a.phone.PackageManagerWrapper();
 //BA.debugLineNum = 60;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(ottplaymedia.mncplaymedia.com.mnchttpjob _mnchttp) throws Exception{
adr.stringfunctions.stringfunctions _sf = null;
String _stmp = "";
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
anywheresoftware.b4a.objects.collections.Map _mresult = null;
byte[] _btmp = null;
anywheresoftware.b4a.agraham.byteconverter.ByteConverter _bctmp = null;
anywheresoftware.b4a.objects.StringUtils _su = null;
 //BA.debugLineNum = 194;BA.debugLine="Sub JobDone (MNChttp As MNCHttpJob)";
 //BA.debugLineNum = 195;BA.debugLine="Dim sf			As StringFunctions";
_sf = new adr.stringfunctions.stringfunctions();
 //BA.debugLineNum = 196;BA.debugLine="Dim sTmp 		As String";
_stmp = "";
 //BA.debugLineNum = 197;BA.debugLine="Dim parser 		As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 198;BA.debugLine="Dim mresult		As Map";
_mresult = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 199;BA.debugLine="Dim bTmp()		As Byte";
_btmp = new byte[(int) (0)];
;
 //BA.debugLineNum = 200;BA.debugLine="Dim bcTmp		As ByteConverter";
_bctmp = new anywheresoftware.b4a.agraham.byteconverter.ByteConverter();
 //BA.debugLineNum = 201;BA.debugLine="Dim su 			As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 204;BA.debugLine="If MNChttp.Success = False Then";
if (_mnchttp._vvvvvvvvvvvvvvvvvvvvvv2==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 205;BA.debugLine="GifMain.Visible = False";
mostCurrent._gifmain.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 207;BA.debugLine="If (sf.InString(MNChttp.ErrorMessage, \"timed out";
if ((_sf._vvv4(_mnchttp._vvvvvvvvvvvvvvvvvvvvvv5,"timed out")>0) || (_sf._vvv4(_mnchttp._vvvvvvvvvvvvvvvvvvvvvv5,"refused")>0) || (_sf._vvv4(_mnchttp._vvvvvvvvvvvvvvvvvvvvvv5,"Unable to resolve host")>0) || (_sf._vvv4(_mnchttp._vvvvvvvvvvvvvvvvvvvvvv5,"UnknownHostException")>0) || (_sf._vvv4(_mnchttp._vvvvvvvvvvvvvvvvvvvvvv5,"FileNotFound")>0)) { 
 //BA.debugLineNum = 210;BA.debugLine="BDLogin.Msgbox(Fungsi.TitleBox, Fungsi.NoKoneks";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvv5.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvv6,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 212;BA.debugLine="If sf.InString(MNChttp.ErrorMessage, \"Not Found";
if (_sf._vvv4(_mnchttp._vvvvvvvvvvvvvvvvvvvvvv5,"Not Found")<0) { 
 //BA.debugLineNum = 213;BA.debugLine="BDLogin.Msgbox(Fungsi.TitleBox, Fungsi.Unknown";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvv5.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvv0,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 };
 };
 //BA.debugLineNum = 217;BA.debugLine="Fungsi.mpList.Put(\"Koneksi\", \"Offline\")";
mostCurrent._vvvvvvvvvvvvvvv0._v5.Put((Object)("Koneksi"),(Object)("Offline"));
 //BA.debugLineNum = 218;BA.debugLine="MNChttp.Release";
_mnchttp._vvvvvvvvvvvvvvvvvvvvv0();
 }else {
 //BA.debugLineNum = 222;BA.debugLine="Try";
try { //BA.debugLineNum = 223;BA.debugLine="bTmp = su.DecodeBase64(MNChttp.GetString)";
_btmp = _su.DecodeBase64(_mnchttp._vvvvvvvvvvvvvvvvvvvvv2());
 //BA.debugLineNum = 224;BA.debugLine="sTmp = bcTmp.StringFromBytes(bTmp, \"UTF8\")";
_stmp = _bctmp.StringFromBytes(_btmp,"UTF8");
 //BA.debugLineNum = 225;BA.debugLine="parser.Initialize(Fungsi.UnWrap(sTmp))";
_parser.Initialize(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvvvv5(mostCurrent.activityBA,_stmp));
 //BA.debugLineNum = 226;BA.debugLine="MNChttp.Release";
_mnchttp._vvvvvvvvvvvvvvvvvvvvv0();
 } 
       catch (Exception e144) {
			processBA.setLastException(e144); //BA.debugLineNum = 228;BA.debugLine="GifMain.Visible = False";
mostCurrent._gifmain.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 229;BA.debugLine="BDLogin.Msgbox(Fungsi.TitleBox, Fungsi.ProsesFa";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvv5.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 //BA.debugLineNum = 230;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 233;BA.debugLine="Try";
try { //BA.debugLineNum = 234;BA.debugLine="mresult.Initialize";
_mresult.Initialize();
 //BA.debugLineNum = 235;BA.debugLine="mresult = parser.NextObject";
_mresult = _parser.NextObject();
 } 
       catch (Exception e152) {
			processBA.setLastException(e152); //BA.debugLineNum = 237;BA.debugLine="GifMain.Visible = False";
mostCurrent._gifmain.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 238;BA.debugLine="BDLogin.Msgbox(Fungsi.TitleBox, Fungsi.ProsesFa";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvv5.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 //BA.debugLineNum = 239;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 242;BA.debugLine="If mresult.Get(\"Result\") <> \"0\" Then";
if ((_mresult.Get((Object)("Result"))).equals((Object)("0")) == false) { 
 //BA.debugLineNum = 243;BA.debugLine="GifMain.Visible = False";
mostCurrent._gifmain.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 244;BA.debugLine="BDLogin.Msgbox(Fungsi.TitleBox, mresult.Get(\"Me";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvv5.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,BA.ObjectToString(_mresult.Get((Object)("Message"))),mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 246;BA.debugLine="Fungsi.mpList.Put(\"Koneksi\", \"Online\")";
mostCurrent._vvvvvvvvvvvvvvv0._v5.Put((Object)("Koneksi"),(Object)("Online"));
 //BA.debugLineNum = 247;BA.debugLine="MNChttp.Release";
_mnchttp._vvvvvvvvvvvvvvvvvvvvv0();
 //BA.debugLineNum = 249;BA.debugLine="Select MNChttp.JobName";
switch (BA.switchObjectToInt(_mnchttp._vvvvvvvvvvvvvvvvvvvvvv1,"Login")) {
case 0:
 //BA.debugLineNum = 252;BA.debugLine="GifMain.Visible = False";
mostCurrent._gifmain.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 254;BA.debugLine="StopService(MNCUtils2Service)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvvv1.getObject()));
 //BA.debugLineNum = 255;BA.debugLine="CancelScheduledService(MNCUtils2Service)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvvv1.getObject()));
 //BA.debugLineNum = 256;BA.debugLine="StopService(ImageDownloader)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()));
 //BA.debugLineNum = 257;BA.debugLine="CancelScheduledService(ImageDownloader)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()));
 //BA.debugLineNum = 258;BA.debugLine="StopService(EPGDownloader)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()));
 //BA.debugLineNum = 259;BA.debugLine="CancelScheduledService(EPGDownloader)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()));
 //BA.debugLineNum = 261;BA.debugLine="Fungsi.DataConfig.Username = etEmail.Text";
mostCurrent._vvvvvvvvvvvvvvv0._v0.Username = mostCurrent._etemail.getText();
 //BA.debugLineNum = 262;BA.debugLine="Fungsi.DataConfig.Password = etPass.Text";
mostCurrent._vvvvvvvvvvvvvvv0._v0.Password = mostCurrent._etpass.getText();
 //BA.debugLineNum = 263;BA.debugLine="Fungsi.TulisFileConfig(Fungsi.DataConfig)";
mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvvvv4(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._v0);
 //BA.debugLineNum = 264;BA.debugLine="StartActivity(Pilihan)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv4.getObject()));
 //BA.debugLineNum = 265;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break;
}
;
 };
 };
 //BA.debugLineNum = 270;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        anywheresoftware.b4a.samples.httputils2.httputils2service._process_globals();
b4a.example.dateutils._process_globals();
main._process_globals();
fungsi._process_globals();
utama._process_globals();
epglist._process_globals();
register._process_globals();
pilihan._process_globals();
epgdownloader._process_globals();
imagedownloader._process_globals();
content._process_globals();
webview._process_globals();
mncutils2service._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 23;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 27;BA.debugLine="End Sub";
return "";
}
public static String  _splash_complete() throws Exception{
anywheresoftware.b4a.objects.collections.List _paclist = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cdback = null;
 //BA.debugLineNum = 105;BA.debugLine="Sub Splash_Complete";
 //BA.debugLineNum = 107;BA.debugLine="If File.Exists(File.DirAssets, \"config.json\") The";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"config.json")) { 
 //BA.debugLineNum = 108;BA.debugLine="If Fungsi.CacahJSON(File.ReadString(File.DirAsse";
if (mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv2(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"config.json"),"NextArray")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 109;BA.debugLine="BDLogin.Msgbox(Fungsi.TitleBox, Fungsi.InitFail";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvv5.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvv4,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 //BA.debugLineNum = 110;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 };
 }else {
 //BA.debugLineNum = 113;BA.debugLine="BDLogin.Msgbox(Fungsi.TitleBox, Fungsi.LoadFail,";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvv5.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvv5,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 //BA.debugLineNum = 114;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 };
 //BA.debugLineNum = 119;BA.debugLine="If (File.Exists(Fungsi.DirDBS, \"nexplayersdklitev";
if ((anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._vvvvvvvvvvvvvvv0._v6,"nexplayersdklitevm.apk")==anywheresoftware.b4a.keywords.Common.False) && (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"nexplayersdklitevm.apk")==anywheresoftware.b4a.keywords.Common.True)) { 
 //BA.debugLineNum = 120;BA.debugLine="ProgressDialogShow2(\"Please wait...\", False)";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow2(mostCurrent.activityBA,"Please wait...",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 121;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 //BA.debugLineNum = 122;BA.debugLine="File.Copy(File.DirAssets, \"nexplayersdklitevm.ap";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"nexplayersdklitevm.apk",mostCurrent._vvvvvvvvvvvvvvv0._v6,"nexplayersdklitevm.apk");
 //BA.debugLineNum = 123;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 124;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 };
 //BA.debugLineNum = 127;BA.debugLine="Dim pacList As List = pm.GetInstalledPackages";
_paclist = new anywheresoftware.b4a.objects.collections.List();
_paclist = mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv6.GetInstalledPackages();
 //BA.debugLineNum = 128;BA.debugLine="ipack = pacList.IndexOf(\"com.nexstreaming.app.nex";
_vvvvvvvvvvvvvvvvvvvvvvvv5 = _paclist.IndexOf((Object)("com.nexstreaming.app.nexplayersample"));
 //BA.debugLineNum = 130;BA.debugLine="If ipack < 0 Then";
if (_vvvvvvvvvvvvvvvvvvvvvvvv5<0) { 
 //BA.debugLineNum = 131;BA.debugLine="ups.Initialize(ups.ACTION_VIEW, \"file://\" & File";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv4.Initialize(mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv4.ACTION_VIEW,"file://"+anywheresoftware.b4a.keywords.Common.File.Combine(mostCurrent._vvvvvvvvvvvvvvv0._v6,"nexplayersdklitevm.apk"));
 //BA.debugLineNum = 132;BA.debugLine="ups.SetType(\"application/vnd.android.package-arc";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv4.SetType("application/vnd.android.package-archive");
 //BA.debugLineNum = 133;BA.debugLine="StartActivity(ups)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv4.getObject()));
 };
 //BA.debugLineNum = 138;BA.debugLine="Activity.LoadLayout(\"login\")";
mostCurrent._activity.LoadLayout("login",mostCurrent.activityBA);
 //BA.debugLineNum = 142;BA.debugLine="Dim cdBack 	As ColorDrawable";
_cdback = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 143;BA.debugLine="cdBack.Initialize(Colors.RGB(132, 31, 40), 5)";
_cdback.Initialize(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (132),(int) (31),(int) (40)),(int) (5));
 //BA.debugLineNum = 144;BA.debugLine="iv1.Background = Fungsi.GetDrawable(\"mail_icon\")";
mostCurrent._iv1.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"mail_icon")));
 //BA.debugLineNum = 145;BA.debugLine="iv2.Background = cdBack";
mostCurrent._iv2.setBackground((android.graphics.drawable.Drawable)(_cdback.getObject()));
 //BA.debugLineNum = 146;BA.debugLine="iv3.Background = Fungsi.GetDrawable(\"password_ico";
mostCurrent._iv3.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"password_icon")));
 //BA.debugLineNum = 147;BA.debugLine="iv4.Background = cdBack";
mostCurrent._iv4.setBackground((android.graphics.drawable.Drawable)(_cdback.getObject()));
 //BA.debugLineNum = 148;BA.debugLine="etEmail.Background = cdBack";
mostCurrent._etemail.setBackground((android.graphics.drawable.Drawable)(_cdback.getObject()));
 //BA.debugLineNum = 149;BA.debugLine="etPass.Background = cdBack";
mostCurrent._etpass.setBackground((android.graphics.drawable.Drawable)(_cdback.getObject()));
 //BA.debugLineNum = 151;BA.debugLine="End Sub";
return "";
}
}
