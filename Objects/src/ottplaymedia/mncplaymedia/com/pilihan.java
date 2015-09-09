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

public class pilihan extends Activity implements B4AActivity{
	public static pilihan mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "ottplaymedia.mncplaymedia.com", "ottplaymedia.mncplaymedia.com.pilihan");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (pilihan).");
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
		activityBA = new BA(this, layout, processBA, "ottplaymedia.mncplaymedia.com", "ottplaymedia.mncplaymedia.com.pilihan");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "ottplaymedia.mncplaymedia.com.pilihan", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (pilihan) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (pilihan) Resume **");
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
		return pilihan.class;
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
        BA.LogInfo("** Activity (pilihan) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (pilihan) Resume **");
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
public anywheresoftware.b4a.objects.ImageViewWrapper _ivlivetv = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivtvod = null;
public flm.b4a.betterdialogs.BetterDialogs _vvvvvvvvvvvvvvvvvvvvvvvv7 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivvod = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivmetube = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _vvvvvvvvvvvvvvv5 = null;
public b4a.example.dateutils _vvvvvvvvvvvvvvv6 = null;
public ottplaymedia.mncplaymedia.com.main _vvvvvvvvvvvvvvv7 = null;
public ottplaymedia.mncplaymedia.com.fungsi _vvvvvvvvvvvvvvv0 = null;
public ottplaymedia.mncplaymedia.com.utama _vvvvvvvvvvvvvvvv1 = null;
public ottplaymedia.mncplaymedia.com.epglist _vvvvvvvvvvvvvvvv2 = null;
public ottplaymedia.mncplaymedia.com.register _vvvvvvvvvvvvvvvv3 = null;
public ottplaymedia.mncplaymedia.com.epgdownloader _vvvvvvvvvvvvvvvv5 = null;
public ottplaymedia.mncplaymedia.com.imagedownloader _vvvvvvvvvvvvvvvv6 = null;
public ottplaymedia.mncplaymedia.com.content _vvvvvvvvvvvvvvvv7 = null;
public ottplaymedia.mncplaymedia.com.webview _vvvvvvvvvvvvvvvv0 = null;
public ottplaymedia.mncplaymedia.com.mncutils2service _vvvvvvvvvvvvvvvvv1 = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 23;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 26;BA.debugLine="Activity.LoadLayout(\"Pilihan\")";
mostCurrent._activity.LoadLayout("Pilihan",mostCurrent.activityBA);
 //BA.debugLineNum = 28;BA.debugLine="ivLiveTV.Background = Fungsi.GetDrawable(\"pilihli";
mostCurrent._ivlivetv.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"pilihlivetv")));
 //BA.debugLineNum = 29;BA.debugLine="ivTVOD.Background = Fungsi.GetDrawable(\"pilihtvod";
mostCurrent._ivtvod.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"pilihtvod")));
 //BA.debugLineNum = 30;BA.debugLine="ivVOD.Background = Fungsi.GetDrawable(\"pilihvod\")";
mostCurrent._ivvod.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"pilihvod")));
 //BA.debugLineNum = 31;BA.debugLine="ivMetube.Background = Fungsi.GetDrawable(\"pilihme";
mostCurrent._ivmetube.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"pilihmetube")));
 //BA.debugLineNum = 32;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 64;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 65;BA.debugLine="If KeyCode = 4 Then ' Back Key";
if (_keycode==4) { 
 //BA.debugLineNum = 66;BA.debugLine="If BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.Keluar";
if (mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv7.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvvv2,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,mostCurrent._vvvvvvvvvvvvvvv0._btncancel,"",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv6),mostCurrent.activityBA)==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 67;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 }else {
 //BA.debugLineNum = 69;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 };
 //BA.debugLineNum = 72;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 46;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 48;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 34;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 35;BA.debugLine="CallSub(ImageDownloader, \"ActivityIsPaused\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()),"ActivityIsPaused");
 //BA.debugLineNum = 36;BA.debugLine="CallSub(EPGDownloader, \"ActivityIsPaused\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()),"ActivityIsPaused");
 //BA.debugLineNum = 38;BA.debugLine="StopService(MNCUtils2Service)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvvv1.getObject()));
 //BA.debugLineNum = 39;BA.debugLine="CancelScheduledService(MNCUtils2Service)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvvv1.getObject()));
 //BA.debugLineNum = 40;BA.debugLine="StopService(ImageDownloader)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()));
 //BA.debugLineNum = 41;BA.debugLine="CancelScheduledService(ImageDownloader)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()));
 //BA.debugLineNum = 42;BA.debugLine="StopService(EPGDownloader)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()));
 //BA.debugLineNum = 43;BA.debugLine="CancelScheduledService(EPGDownloader)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()));
 //BA.debugLineNum = 44;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 16;BA.debugLine="Private ivLiveTV 	As ImageView";
mostCurrent._ivlivetv = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Private ivTVOD 		As ImageView";
mostCurrent._ivtvod = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private BDUtama		As BetterDialogs";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv7 = new flm.b4a.betterdialogs.BetterDialogs();
 //BA.debugLineNum = 19;BA.debugLine="Private ivVOD 		As ImageView";
mostCurrent._ivvod = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private ivMetube 	As ImageView";
mostCurrent._ivmetube = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 21;BA.debugLine="End Sub";
return "";
}
public static String  _ivlivetv_click() throws Exception{
 //BA.debugLineNum = 50;BA.debugLine="Sub ivLiveTV_Click";
 //BA.debugLineNum = 51;BA.debugLine="Fungsi.DataConfig.sTab = \"LiveTV\"";
mostCurrent._vvvvvvvvvvvvvvv0._v0.sTab = "LiveTV";
 //BA.debugLineNum = 52;BA.debugLine="Fungsi.tulisfileconfig(Fungsi.DataConfig)";
mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvvvv4(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._v0);
 //BA.debugLineNum = 53;BA.debugLine="StartActivity(Utama)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv1.getObject()));
 //BA.debugLineNum = 54;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 55;BA.debugLine="End Sub";
return "";
}
public static String  _ivmetube_click() throws Exception{
 //BA.debugLineNum = 81;BA.debugLine="Sub ivMetube_Click";
 //BA.debugLineNum = 83;BA.debugLine="StartActivity(Webview)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv0.getObject()));
 //BA.debugLineNum = 86;BA.debugLine="Return";
if (true) return "";
 //BA.debugLineNum = 87;BA.debugLine="End Sub";
return "";
}
public static String  _ivtvod_click() throws Exception{
 //BA.debugLineNum = 57;BA.debugLine="Sub ivTVOD_Click";
 //BA.debugLineNum = 58;BA.debugLine="Fungsi.DataConfig.sTab = \"TVOD\"";
mostCurrent._vvvvvvvvvvvvvvv0._v0.sTab = "TVOD";
 //BA.debugLineNum = 59;BA.debugLine="Fungsi.tulisfileconfig(Fungsi.DataConfig)";
mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvvvv4(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._v0);
 //BA.debugLineNum = 60;BA.debugLine="StartActivity(Utama)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv1.getObject()));
 //BA.debugLineNum = 61;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 62;BA.debugLine="End Sub";
return "";
}
public static String  _ivvod_click() throws Exception{
 //BA.debugLineNum = 74;BA.debugLine="Sub ivVOD_Click";
 //BA.debugLineNum = 75;BA.debugLine="Fungsi.DataConfig.sTab = \"VOD\"";
mostCurrent._vvvvvvvvvvvvvvv0._v0.sTab = "VOD";
 //BA.debugLineNum = 76;BA.debugLine="Fungsi.tulisfileconfig(Fungsi.DataConfig)";
mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvvvv4(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._v0);
 //BA.debugLineNum = 77;BA.debugLine="StartActivity(Utama)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv1.getObject()));
 //BA.debugLineNum = 78;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 79;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
}
