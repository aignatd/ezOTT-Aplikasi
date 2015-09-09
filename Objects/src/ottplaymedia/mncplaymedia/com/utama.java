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

public class utama extends Activity implements B4AActivity{
	public static utama mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "ottplaymedia.mncplaymedia.com", "ottplaymedia.mncplaymedia.com.utama");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (utama).");
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
		activityBA = new BA(this, layout, processBA, "ottplaymedia.mncplaymedia.com", "ottplaymedia.mncplaymedia.com.utama");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "ottplaymedia.mncplaymedia.com.utama", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (utama) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (utama) Resume **");
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
		return utama.class;
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
        BA.LogInfo("** Activity (utama) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (utama) Resume **");
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
public anywheresoftware.b4a.objects.collections.Map _vvvvvvvvv7 = null;
public anywheresoftware.b4a.phone.Phone _vvv4 = null;
public static int _vvvvvvvvvvvvvvvvvvvvvvvv0 = 0;
public static int _vvvvvvvvvvvvvvvvvvvvvvvvv1 = 0;
public static int _vvvvvvvvvvvvvvvvvvvvvvvvv2 = 0;
public static int _vvvvvvvvvvvvvvvvvvvvvvvvv3 = 0;
public static int _vvvvvvvvvvvvvvvvvvvvvvvvv4 = 0;
public static int _vvvvvvvvvvvvvvvvvvvvvvvvv5 = 0;
public flm.b4a.betterdialogs.BetterDialogs _vvvvvvvvvvvvvvvvvvvvvvvv7 = null;
public anywheresoftware.b4a.objects.PanelWrapper _pbmain = null;
public ottplaymedia.mncplaymedia.com.mnchttpjob _vvvvvvvvvvvvvvvvvvvvvvvvvv3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblfeat = null;
public com.datasteam.b4a.xtraviews.MovieViewControl _gifsmart = null;
public anywheresoftware.b4a.objects.PanelWrapper _pamain = null;
public anywheresoftware.b4a.objects.PanelWrapper _ptengah = null;
public com.datasteam.b4a.xtraviews.MovieViewControl _cvthumdet = null;
public anywheresoftware.b4a.objects.collections.Map _vvvvvvvvvvvvvvvvvvvvvvvvvv2 = null;
public anywheresoftware.b4a.objects.collections.Map _vvvvvvvvvvvvvvvvvvvvvvvvvv1 = null;
public anywheresoftware.b4a.objects.IntentWrapper _vvvvvvvvvvvvvvvvvvvvvvvv4 = null;
public static int _vvvvvvvvvvvvvvvvvvvvvvvv5 = 0;
public anywheresoftware.b4a.phone.PackageManagerWrapper _vvvvvvvvvvvvvvvvvvvvvvvv6 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivepgdet = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivepgdet2 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _vvvvvvvvvvvvvvvvvvvvvvvvvvv2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblepgdet = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivthumdet = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _vvvvvvvvvvvvvvvvvvvvvvvvv6 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _vvvvvvvvvvvvvvvvvvvvvvvvv7 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _vvvvvvvvvvvvvvvvvvvvvvvvv0 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivlivetv = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivtvod = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivvod = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivfav = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblgenre = null;
public anywheresoftware.b4a.objects.collections.Map _vvvvvvvvv0 = null;
public anywheresoftware.b4a.objects.collections.Map _vvvvvvvvvv1 = null;
public anywheresoftware.b4a.randomaccessfile.RandomAccessFile _vv1 = null;
public anywheresoftware.b4a.objects.collections.Map _vvvvvvvvvv2 = null;
public ottplaymedia.mncplaymedia.com.keyvaluestore _vvvvvvvvvv3 = null;
public static int _vvvvvvvvvv4 = 0;
public anywheresoftware.b4a.samples.httputils2.httputils2service _vvvvvvvvvvvvvvv5 = null;
public b4a.example.dateutils _vvvvvvvvvvvvvvv6 = null;
public ottplaymedia.mncplaymedia.com.main _vvvvvvvvvvvvvvv7 = null;
public ottplaymedia.mncplaymedia.com.fungsi _vvvvvvvvvvvvvvv0 = null;
public ottplaymedia.mncplaymedia.com.epglist _vvvvvvvvvvvvvvvv2 = null;
public ottplaymedia.mncplaymedia.com.register _vvvvvvvvvvvvvvvv3 = null;
public ottplaymedia.mncplaymedia.com.pilihan _vvvvvvvvvvvvvvvv4 = null;
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
anywheresoftware.b4a.objects.collections.List _paclist = null;
 //BA.debugLineNum = 78;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 81;BA.debugLine="mlbFav.Initialize";
mostCurrent._vvvvvvvvvv2.Initialize();
 //BA.debugLineNum = 83;BA.debugLine="If File.Exists(File.DirDefaultExternal, Fungsi.";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal(),mostCurrent._vvvvvvvvvvvvvvv0._v0.Username+"_label")) { 
 //BA.debugLineNum = 84;BA.debugLine="mlbFav = File.ReadMap(File.DirDefaultExternal,";
mostCurrent._vvvvvvvvvv2 = anywheresoftware.b4a.keywords.Common.File.ReadMap(anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal(),mostCurrent._vvvvvvvvvvvvvvv0._v0.Username+"_label");
 };
 //BA.debugLineNum = 87;BA.debugLine="keyFav.Initialize(File.DirDefaultExternal, Fung";
mostCurrent._vvvvvvvvvv3._initialize(processBA,anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal(),mostCurrent._vvvvvvvvvvvvvvv0._v0.Username+"_gambar");
 //BA.debugLineNum = 90;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 91;BA.debugLine="Try";
try { //BA.debugLineNum = 93;BA.debugLine="Fungsi.SetDirProg(\"\")";
mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvvvv1(mostCurrent.activityBA,"");
 //BA.debugLineNum = 94;BA.debugLine="Fungsi.BacaFileConfig";
mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv1(mostCurrent.activityBA);
 //BA.debugLineNum = 95;BA.debugLine="Fungsi.mpList.Put(\"DirDBS\", Fungsi.DirDBS)";
mostCurrent._vvvvvvvvvvvvvvv0._v5.Put((Object)("DirDBS"),(Object)(mostCurrent._vvvvvvvvvvvvvvv0._v6));
 } 
       catch (Exception e56) {
			processBA.setLastException(e56); //BA.debugLineNum = 97;BA.debugLine="BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.InitFail";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv7.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvv4,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 //BA.debugLineNum = 98;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 };
 };
 //BA.debugLineNum = 103;BA.debugLine="If GetDeviceLayoutValues.ApproximateScreenSize <";
if (anywheresoftware.b4a.keywords.Common.GetDeviceLayoutValues(mostCurrent.activityBA).getApproximateScreenSize()<5) { 
 //BA.debugLineNum = 105;BA.debugLine="If 100%x > 100%y Then";
if (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)>anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA)) { 
 //BA.debugLineNum = 106;BA.debugLine="Spasi = -22";
_vvvvvvvvvvvvvvvvvvvvvvvv0 = (int) (-22);
 //BA.debugLineNum = 107;BA.debugLine="Lebar = 45%x";
_vvvvvvvvvvvvvvvvvvvvvvvvv1 = anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (45),mostCurrent.activityBA);
 //BA.debugLineNum = 108;BA.debugLine="Tinggi = 35%y";
_vvvvvvvvvvvvvvvvvvvvvvvvv2 = anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (35),mostCurrent.activityBA);
 //BA.debugLineNum = 109;BA.debugLine="Durasi = 800";
_vvvvvvvvvvvvvvvvvvvvvvvvv3 = (int) (800);
 //BA.debugLineNum = 110;BA.debugLine="jeday = 0.2%y";
_vvvvvvvvvvvvvvvvvvvvvvvvv4 = anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (0.2),mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 112;BA.debugLine="Spasi = -17";
_vvvvvvvvvvvvvvvvvvvvvvvv0 = (int) (-17);
 //BA.debugLineNum = 113;BA.debugLine="Lebar = 45%x";
_vvvvvvvvvvvvvvvvvvvvvvvvv1 = anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (45),mostCurrent.activityBA);
 //BA.debugLineNum = 114;BA.debugLine="Tinggi = 35%y";
_vvvvvvvvvvvvvvvvvvvvvvvvv2 = anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (35),mostCurrent.activityBA);
 //BA.debugLineNum = 115;BA.debugLine="Durasi = 800";
_vvvvvvvvvvvvvvvvvvvvvvvvv3 = (int) (800);
 //BA.debugLineNum = 116;BA.debugLine="jeday = 0.5%y";
_vvvvvvvvvvvvvvvvvvvvvvvvv4 = anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (0.5),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 119;BA.debugLine="Device = 0";
_vvvvvvvvvvvvvvvvvvvvvvvvv5 = (int) (0);
 //BA.debugLineNum = 120;BA.debugLine="Activity.LoadLayout(\"smartphone\")";
mostCurrent._activity.LoadLayout("smartphone",mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 124;BA.debugLine="Spasi = -75";
_vvvvvvvvvvvvvvvvvvvvvvvv0 = (int) (-75);
 //BA.debugLineNum = 125;BA.debugLine="Lebar = 50%x";
_vvvvvvvvvvvvvvvvvvvvvvvvv1 = anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (50),mostCurrent.activityBA);
 //BA.debugLineNum = 126;BA.debugLine="Tinggi = 40%y";
_vvvvvvvvvvvvvvvvvvvvvvvvv2 = anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (40),mostCurrent.activityBA);
 //BA.debugLineNum = 127;BA.debugLine="Durasi = 700";
_vvvvvvvvvvvvvvvvvvvvvvvvv3 = (int) (700);
 //BA.debugLineNum = 128;BA.debugLine="jeday = 0.5%y";
_vvvvvvvvvvvvvvvvvvvvvvvvv4 = anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (0.5),mostCurrent.activityBA);
 //BA.debugLineNum = 130;BA.debugLine="Device = 1";
_vvvvvvvvvvvvvvvvvvvvvvvvv5 = (int) (1);
 //BA.debugLineNum = 131;BA.debugLine="Activity.LoadLayout(\"tablet\")";
mostCurrent._activity.LoadLayout("tablet",mostCurrent.activityBA);
 };
 //BA.debugLineNum = 137;BA.debugLine="If File.Exists(File.DirAssets, \"config.json\") The";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"config.json")) { 
 //BA.debugLineNum = 138;BA.debugLine="If Fungsi.CacahJSON(File.ReadString(File.DirAsse";
if (mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv2(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"config.json"),"NextArray")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 139;BA.debugLine="BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.InitFail";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv7.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvv4,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 //BA.debugLineNum = 140;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 };
 }else {
 //BA.debugLineNum = 143;BA.debugLine="BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.LoadFail,";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv7.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvv5,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 //BA.debugLineNum = 144;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 };
 //BA.debugLineNum = 149;BA.debugLine="Dim pacList As List = pm.GetInstalledPackages";
_paclist = new anywheresoftware.b4a.objects.collections.List();
_paclist = mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv6.GetInstalledPackages();
 //BA.debugLineNum = 150;BA.debugLine="ipack = pacList.IndexOf(\"com.nexstreaming.app.nex";
_vvvvvvvvvvvvvvvvvvvvvvvv5 = _paclist.IndexOf((Object)("com.nexstreaming.app.nexplayersample"));
 //BA.debugLineNum = 152;BA.debugLine="If ipack < 0 Then";
if (_vvvvvvvvvvvvvvvvvvvvvvvv5<0) { 
 //BA.debugLineNum = 153;BA.debugLine="ups.Initialize(ups.ACTION_VIEW, \"file://\" & File";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv4.Initialize(mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv4.ACTION_VIEW,"file://"+anywheresoftware.b4a.keywords.Common.File.Combine(mostCurrent._vvvvvvvvvvvvvvv0._v6,"nexplayersdklitevm.apk"));
 //BA.debugLineNum = 154;BA.debugLine="ups.SetType(\"application/vnd.android.package-arc";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv4.SetType("application/vnd.android.package-archive");
 //BA.debugLineNum = 155;BA.debugLine="StartActivity(ups)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv4.getObject()));
 };
 //BA.debugLineNum = 159;BA.debugLine="bmpTemp.Initialize(\"\")";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvv6.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 160;BA.debugLine="bmpTemp.Background = Fungsi.GetDrawable(\"icoplay\"";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvv6.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"icoplay")));
 //BA.debugLineNum = 161;BA.debugLine="bmpTemp.Gravity = Gravity.FILL";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvv6.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 //BA.debugLineNum = 163;BA.debugLine="bmpFav.Initialize(\"\")";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvv7.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 164;BA.debugLine="bmpFav.Background = Fungsi.GetDrawable(\"ico_favor";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvv7.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"ico_favorite")));
 //BA.debugLineNum = 165;BA.debugLine="bmpFav.Gravity = Gravity.FILL";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvv7.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 //BA.debugLineNum = 167;BA.debugLine="bmpDelFav.Initialize(\"\")";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvv0.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 168;BA.debugLine="bmpDelFav.Background = Fungsi.GetDrawable(\"ico_de";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvv0.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"ico_delfav")));
 //BA.debugLineNum = 169;BA.debugLine="bmpDelFav.Gravity = Gravity.FILL";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvv0.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 //BA.debugLineNum = 172;BA.debugLine="GifSmart.Load(File.DirAssets, \"spin.gif\", True)";
mostCurrent._gifsmart.Load(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"spin.gif",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 173;BA.debugLine="GifSmart.Visible = False";
mostCurrent._gifsmart.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 174;BA.debugLine="MapTampung.Initialize";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvv1.Initialize();
 //BA.debugLineNum = 175;BA.debugLine="picture.Initialize";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvv2.Initialize();
 //BA.debugLineNum = 177;BA.debugLine="init_layout";
_init_layout();
 //BA.debugLineNum = 178;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 644;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 646;BA.debugLine="If KeyCode = 4 Then ' Back Key";
if (_keycode==4) { 
 //BA.debugLineNum = 648;BA.debugLine="CallSub(ImageDownloader, \"ActivityIsPaused\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()),"ActivityIsPaused");
 //BA.debugLineNum = 649;BA.debugLine="CallSub(EPGDownloader, \"ActivityIsPaused\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()),"ActivityIsPaused");
 //BA.debugLineNum = 651;BA.debugLine="StopService(MNCUtils2Service)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvvv1.getObject()));
 //BA.debugLineNum = 652;BA.debugLine="CancelScheduledService(MNCUtils2Service)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvvv1.getObject()));
 //BA.debugLineNum = 653;BA.debugLine="StopService(ImageDownloader)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()));
 //BA.debugLineNum = 654;BA.debugLine="CancelScheduledService(ImageDownloader)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()));
 //BA.debugLineNum = 655;BA.debugLine="StopService(EPGDownloader)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()));
 //BA.debugLineNum = 656;BA.debugLine="CancelScheduledService(EPGDownloader)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()));
 //BA.debugLineNum = 658;BA.debugLine="File.WriteMap(File.DirDefaultExternal, Fungsi.Da";
anywheresoftware.b4a.keywords.Common.File.WriteMap(anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal(),mostCurrent._vvvvvvvvvvvvvvv0._v0.Username+"_label",mostCurrent._vvvvvvvvvv2);
 //BA.debugLineNum = 660;BA.debugLine="StartActivity(Pilihan)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv4.getObject()));
 //BA.debugLineNum = 661;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 }else {
 //BA.debugLineNum = 663;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 665;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 198;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 199;BA.debugLine="CallSub(ImageDownloader, \"ActivityIsPaused\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()),"ActivityIsPaused");
 //BA.debugLineNum = 200;BA.debugLine="CallSub(EPGDownloader, \"ActivityIsPaused\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()),"ActivityIsPaused");
 //BA.debugLineNum = 201;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 180;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 181;BA.debugLine="Fungsi.BacaFileConfig";
mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv1(mostCurrent.activityBA);
 //BA.debugLineNum = 182;BA.debugLine="Fungsi.DirDBS = Fungsi.mpList.Get(\"DirDBS\")";
mostCurrent._vvvvvvvvvvvvvvv0._v6 = BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("DirDBS")));
 //BA.debugLineNum = 184;BA.debugLine="If ipack < 0 Then";
if (_vvvvvvvvvvvvvvvvvvvvvvvv5<0) { 
 //BA.debugLineNum = 185;BA.debugLine="BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.NoPlayer,";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv7.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvvv0,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 187;BA.debugLine="ups.Initialize(ups.ACTION_MAIN, \"\")";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv4.Initialize(mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv4.ACTION_MAIN,"");
 //BA.debugLineNum = 188;BA.debugLine="ups = pm.GetApplicationIntent(\"com.nexstreaming.";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv4 = mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv6.GetApplicationIntent("com.nexstreaming.app.nexplayersample");
 //BA.debugLineNum = 189;BA.debugLine="ups.PutExtra(\"Company\", \"PT-Infokom\")";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv4.PutExtra("Company",(Object)("PT-Infokom"));
 //BA.debugLineNum = 190;BA.debugLine="ups.PutExtra(\"VCASAddr\", \"202.80.222.36:80\")";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv4.PutExtra("VCASAddr",(Object)("202.80.222.36:80"));
 };
 //BA.debugLineNum = 193;BA.debugLine="Cek_Koneksi";
_cek_koneksi();
 //BA.debugLineNum = 196;BA.debugLine="End Sub";
return "";
}
public static String  _cek_koneksi() throws Exception{
 //BA.debugLineNum = 266;BA.debugLine="Sub Cek_Koneksi";
 //BA.debugLineNum = 267;BA.debugLine="GifSmart.Visible = True";
mostCurrent._gifsmart.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 269;BA.debugLine="CallSub(ImageDownloader, \"ActivityIsPaused\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()),"ActivityIsPaused");
 //BA.debugLineNum = 270;BA.debugLine="CallSub(EPGDownloader, \"ActivityIsPaused\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()),"ActivityIsPaused");
 //BA.debugLineNum = 272;BA.debugLine="StopService(MNCUtils2Service)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvvv1.getObject()));
 //BA.debugLineNum = 273;BA.debugLine="CancelScheduledService(MNCUtils2Service)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvvv1.getObject()));
 //BA.debugLineNum = 274;BA.debugLine="StopService(ImageDownloader)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()));
 //BA.debugLineNum = 275;BA.debugLine="CancelScheduledService(ImageDownloader)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()));
 //BA.debugLineNum = 276;BA.debugLine="StopService(EPGDownloader)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()));
 //BA.debugLineNum = 277;BA.debugLine="CancelScheduledService(EPGDownloader)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()));
 //BA.debugLineNum = 279;BA.debugLine="MNCJob.Initialize(\"Koneksi\", Me)";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvv3._initialize(processBA,"Koneksi",utama.getObject());
 //BA.debugLineNum = 281;BA.debugLine="Try";
try { //BA.debugLineNum = 282;BA.debugLine="MNCJob.Download(\"http://clips.mncplaymedia.net\")";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvv3._download("http://clips.mncplaymedia.net");
 } 
       catch (Exception e200) {
			processBA.setLastException(e200); //BA.debugLineNum = 284;BA.debugLine="MNCJob.Release";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvv3._vvvvvvvvvvvvvvvvvvvvv0();
 //BA.debugLineNum = 285;BA.debugLine="Fungsi.mpList.Put(\"Koneksi\", \"Offline\")";
mostCurrent._vvvvvvvvvvvvvvv0._v5.Put((Object)("Koneksi"),(Object)("Offline"));
 //BA.debugLineNum = 286;BA.debugLine="GifSmart.Visible = False";
mostCurrent._gifsmart.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 287;BA.debugLine="BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.NoKoneksi";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv7.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvv6,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 289;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvvvvvvvvvvvvvvvvvvvvvv4() throws Exception{
 //BA.debugLineNum = 939;BA.debugLine="Sub DelItemFav";
 //BA.debugLineNum = 940;BA.debugLine="pTengah.RemoveAllViews";
mostCurrent._ptengah.RemoveAllViews();
 //BA.debugLineNum = 941;BA.debugLine="GetListFavorite";
_vvvvvvvvvvvvvvvvvvvvvvvvvv5();
 //BA.debugLineNum = 942;BA.debugLine="End Sub";
return "";
}
public static String  _evfavorite_click() throws Exception{
anywheresoftware.b4a.objects.ImageViewWrapper _ivtemp2 = null;
anywheresoftware.b4a.objects.PanelWrapper _fp = null;
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
anywheresoftware.b4a.objects.LabelWrapper _bcoba = null;
anywheresoftware.b4a.objects.ImageViewWrapper _ivcoba = null;
String[] _pisah = null;
 //BA.debugLineNum = 888;BA.debugLine="Sub evFavorite_Click";
 //BA.debugLineNum = 889;BA.debugLine="Dim ivTemp2		As ImageView = Sender";
_ivtemp2 = new anywheresoftware.b4a.objects.ImageViewWrapper();
_ivtemp2.setObject((android.widget.ImageView)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 890;BA.debugLine="Dim Fp			As Panel	 = Fungsi.GetParent(ivTemp2)";
_fp = new anywheresoftware.b4a.objects.PanelWrapper();
_fp.setObject((android.view.ViewGroup)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvvv4(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(_ivtemp2.getObject()))).getObject()));
 //BA.debugLineNum = 893;BA.debugLine="If ivTemp2.Tag  = \"Putih#None\" Then";
if ((_ivtemp2.getTag()).equals((Object)("Putih#None"))) { 
 //BA.debugLineNum = 894;BA.debugLine="ivTemp2.Tag = \"Merah#None\"";
_ivtemp2.setTag((Object)("Merah#None"));
 //BA.debugLineNum = 895;BA.debugLine="ivTemp2.Background = Fungsi.GetDrawable(\"ico_fav";
_ivtemp2.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"ico_favoritered")));
 }else if((_ivtemp2.getTag()).equals((Object)("Merah#None"))) { 
 //BA.debugLineNum = 897;BA.debugLine="ivTemp2.Tag		= \"Putih#None\"";
_ivtemp2.setTag((Object)("Putih#None"));
 //BA.debugLineNum = 898;BA.debugLine="ivTemp2.Background = Fungsi.GetDrawable(\"ico_fav";
_ivtemp2.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"ico_favorite")));
 };
 //BA.debugLineNum = 902;BA.debugLine="For Each v As View In  Fp.GetAllViewsRecursive";
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
final anywheresoftware.b4a.BA.IterableList group670 = _fp.GetAllViewsRecursive();
final int groupLen670 = group670.getSize();
for (int index670 = 0;index670 < groupLen670 ;index670++){
_v.setObject((android.view.View)(group670.Get(index670)));
 //BA.debugLineNum = 903;BA.debugLine="If v Is Label Then";
if (_v.getObjectOrNull() instanceof android.widget.TextView) { 
 //BA.debugLineNum = 904;BA.debugLine="Dim bcoba  As Label = v";
_bcoba = new anywheresoftware.b4a.objects.LabelWrapper();
_bcoba.setObject((android.widget.TextView)(_v.getObject()));
 //BA.debugLineNum = 906;BA.debugLine="If ivTemp2.Tag = \"Merah#None\" Then";
if ((_ivtemp2.getTag()).equals((Object)("Merah#None"))) { 
 //BA.debugLineNum = 907;BA.debugLine="mlbFav.Put(bcoba.tag, bcoba.text)";
mostCurrent._vvvvvvvvvv2.Put(_bcoba.getTag(),(Object)(_bcoba.getText()));
 }else if((_ivtemp2.getTag()).equals((Object)("Putih#None"))) { 
 //BA.debugLineNum = 909;BA.debugLine="mlbFav.Remove(bcoba.tag)";
mostCurrent._vvvvvvvvvv2.Remove(_bcoba.getTag());
 }else if((_ivtemp2.getTag()).equals((Object)("Delete#None"))) { 
 //BA.debugLineNum = 911;BA.debugLine="mlbFav.Remove(bcoba.tag)";
mostCurrent._vvvvvvvvvv2.Remove(_bcoba.getTag());
 };
 //BA.debugLineNum = 914;BA.debugLine="Log(mlbFav.size)";
anywheresoftware.b4a.keywords.Common.Log(BA.NumberToString(mostCurrent._vvvvvvvvvv2.getSize()));
 }else if(_v.getObjectOrNull() instanceof android.widget.ImageView) { 
 //BA.debugLineNum = 916;BA.debugLine="Dim ivcoba As ImageView = v";
_ivcoba = new anywheresoftware.b4a.objects.ImageViewWrapper();
_ivcoba.setObject((android.widget.ImageView)(_v.getObject()));
 //BA.debugLineNum = 917;BA.debugLine="Dim Pisah() As String";
_pisah = new String[(int) (0)];
java.util.Arrays.fill(_pisah,"");
 //BA.debugLineNum = 919;BA.debugLine="Pisah = Regex.Split(\"#\", ivcoba.Tag)";
_pisah = anywheresoftware.b4a.keywords.Common.Regex.Split("#",BA.ObjectToString(_ivcoba.getTag()));
 //BA.debugLineNum = 921;BA.debugLine="If Pisah(1) = \"Thumb\" Then";
if ((_pisah[(int) (1)]).equals("Thumb")) { 
 //BA.debugLineNum = 922;BA.debugLine="If ivTemp2.Tag = \"Merah#None\" Then";
if ((_ivtemp2.getTag()).equals((Object)("Merah#None"))) { 
 //BA.debugLineNum = 923;BA.debugLine="keyFav.PutBitmap(Pisah(2) & \"#\" & Pisah(3),";
mostCurrent._vvvvvvvvvv3._vvvvvvvvvvvvvv3(_pisah[(int) (2)]+"#"+_pisah[(int) (3)],(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(_ivcoba.getBitmap())));
 //BA.debugLineNum = 924;BA.debugLine="keyFav.PutSimple(Pisah(2) & \"#\" & Pisah(3) &";
mostCurrent._vvvvvvvvvv3._vvvvvvvvvvvvvv0(_pisah[(int) (2)]+"#"+_pisah[(int) (3)]+"#Label",_ivcoba.getTag());
 //BA.debugLineNum = 925;BA.debugLine="WriteMapFile";
_vvvvvvvvvvvvvvvvvvvvvvvvvv6();
 }else if((_ivtemp2.getTag()).equals((Object)("Putih#None"))) { 
 //BA.debugLineNum = 927;BA.debugLine="keyFav.Remove(Pisah(2) & \"#\" & Pisah(3))";
mostCurrent._vvvvvvvvvv3._vvvvvvvvvvvvvvv1(_pisah[(int) (2)]+"#"+_pisah[(int) (3)]);
 //BA.debugLineNum = 928;BA.debugLine="WriteMapFile";
_vvvvvvvvvvvvvvvvvvvvvvvvvv6();
 }else if((_ivtemp2.getTag()).equals((Object)("Delete#None"))) { 
 //BA.debugLineNum = 930;BA.debugLine="keyFav.Remove(Pisah(2) & \"#\" & Pisah(3))";
mostCurrent._vvvvvvvvvv3._vvvvvvvvvvvvvvv1(_pisah[(int) (2)]+"#"+_pisah[(int) (3)]);
 //BA.debugLineNum = 931;BA.debugLine="WriteMapFile";
_vvvvvvvvvvvvvvvvvvvvvvvvvv6();
 //BA.debugLineNum = 932;BA.debugLine="DelItemFav";
_vvvvvvvvvvvvvvvvvvvvvvvvvv4();
 };
 };
 };
 }
;
 //BA.debugLineNum = 937;BA.debugLine="End Sub";
return "";
}
public static String  _evplayvideo_click() throws Exception{
anywheresoftware.b4a.objects.ImageViewWrapper _ivtemp = null;
ottplaymedia.mncplaymedia.com.mnchttpjob _chnjob = null;
String[] _pisah = null;
 //BA.debugLineNum = 785;BA.debugLine="Sub evPlayVideo_Click";
 //BA.debugLineNum = 786;BA.debugLine="Dim ivTemp	As ImageView = Sender";
_ivtemp = new anywheresoftware.b4a.objects.ImageViewWrapper();
_ivtemp.setObject((android.widget.ImageView)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 787;BA.debugLine="Dim ChnJob 	As MNCHttpJob";
_chnjob = new ottplaymedia.mncplaymedia.com.mnchttpjob();
 //BA.debugLineNum = 788;BA.debugLine="Dim Pisah()	As String";
_pisah = new String[(int) (0)];
java.util.Arrays.fill(_pisah,"");
 //BA.debugLineNum = 790;BA.debugLine="Pisah = Regex.Split(\"#\", ivTemp.Tag)";
_pisah = anywheresoftware.b4a.keywords.Common.Regex.Split("#",BA.ObjectToString(_ivtemp.getTag()));
 //BA.debugLineNum = 791;BA.debugLine="GifSmart.Visible = True";
mostCurrent._gifsmart.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 792;BA.debugLine="ChnJob.Initialize(\"GetURLLiveTV\", Me)";
_chnjob._initialize(processBA,"GetURLLiveTV",utama.getObject());
 //BA.debugLineNum = 794;BA.debugLine="Try";
try { //BA.debugLineNum = 795;BA.debugLine="ChnJob.Download(Pisah(0))";
_chnjob._download(_pisah[(int) (0)]);
 //BA.debugLineNum = 796;BA.debugLine="ChnJob.GetRequest.SetHeader(\"Cache-Control\", \"no";
_chnjob._vvvvvvvvvvvvvvvvvvvvv1().SetHeader("Cache-Control","no-cache, must-revalidate");
 //BA.debugLineNum = 797;BA.debugLine="ChnJob.GetRequest.Timeout = DateTime.TicksPerSec";
_chnjob._vvvvvvvvvvvvvvvvvvvvv1().setTimeout((int) (anywheresoftware.b4a.keywords.Common.DateTime.TicksPerSecond*60));
 //BA.debugLineNum = 798;BA.debugLine="ChnJob.Tag = ivTemp.Tag";
_chnjob._vvvvvvvvvvvvvvvvvvvvvvv1 = _ivtemp.getTag();
 } 
       catch (Exception e590) {
			processBA.setLastException(e590); //BA.debugLineNum = 800;BA.debugLine="ChnJob.Release";
_chnjob._vvvvvvvvvvvvvvvvvvvvv0();
 //BA.debugLineNum = 801;BA.debugLine="Fungsi.mpList.Put(\"Channel\", \"No Channel View\")";
mostCurrent._vvvvvvvvvvvvvvv0._v5.Put((Object)("Channel"),(Object)("No Channel View"));
 //BA.debugLineNum = 802;BA.debugLine="BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.NoGetList";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv7.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvvv3,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 804;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvvvvvvvvvvvvvvvvvvvvvv5() throws Exception{
anywheresoftware.b4a.objects.ScrollViewWrapper _svtengah = null;
int _i = 0;
anywheresoftware.b4a.objects.PanelWrapper _panepg = null;
anywheresoftware.b4a.objects.LabelWrapper _ltitle = null;
anywheresoftware.b4a.objects.ImageViewWrapper _ivthumbfav = null;
anywheresoftware.b4a.objects.ImageViewWrapper _ivplay = null;
 //BA.debugLineNum = 741;BA.debugLine="Sub GetListFavorite";
 //BA.debugLineNum = 742;BA.debugLine="Dim svtengah As ScrollView";
_svtengah = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 743;BA.debugLine="svtengah.Initialize(0)";
_svtengah.Initialize(mostCurrent.activityBA,(int) (0));
 //BA.debugLineNum = 744;BA.debugLine="svtengah.Panel.RemoveAllViews";
_svtengah.getPanel().RemoveAllViews();
 //BA.debugLineNum = 745;BA.debugLine="pTengah.AddView(svtengah, 0, 1.5%y, pTengah.Width";
mostCurrent._ptengah.AddView((android.view.View)(_svtengah.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (1.5),mostCurrent.activityBA),mostCurrent._ptengah.getWidth(),(int) (mostCurrent._ptengah.getHeight()-anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (3),mostCurrent.activityBA)));
 //BA.debugLineNum = 747;BA.debugLine="For i=0 To mlbFav.Size-1";
{
final int step548 = 1;
final int limit548 = (int) (mostCurrent._vvvvvvvvvv2.getSize()-1);
for (_i = (int) (0); (step548 > 0 && _i <= limit548) || (step548 < 0 && _i >= limit548); _i = ((int)(0 + _i + step548))) {
 //BA.debugLineNum = 748;BA.debugLine="Dim PanEPG			As Panel";
_panepg = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 749;BA.debugLine="Dim lTitle			As Label";
_ltitle = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 750;BA.debugLine="Dim ivThumbFav		As ImageView";
_ivthumbfav = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 751;BA.debugLine="Dim ivPlay			As ImageView";
_ivplay = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 753;BA.debugLine="PanEPG.Initialize(\"\")";
_panepg.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 754;BA.debugLine="ivPlay.Initialize(\"\")";
_ivplay.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 756;BA.debugLine="If Device = 0 Then";
if (_vvvvvvvvvvvvvvvvvvvvvvvvv5==0) { 
 //BA.debugLineNum = 757;BA.debugLine="PanEPG.LoadLayout(\"LiveTVDetSmart\")";
_panepg.LoadLayout("LiveTVDetSmart",mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 759;BA.debugLine="PanEPG.LoadLayout(\"LiveTVDetail\")";
_panepg.LoadLayout("LiveTVDetail",mostCurrent.activityBA);
 };
 //BA.debugLineNum = 762;BA.debugLine="ivEPGDet.Background = bmpTemp.Background";
mostCurrent._ivepgdet.setBackground(mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvv6.getBackground());
 //BA.debugLineNum = 763;BA.debugLine="ivEPGDet2.Background = bmpDelFav.Background";
mostCurrent._ivepgdet2.setBackground(mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvv0.getBackground());
 //BA.debugLineNum = 764;BA.debugLine="lblEPGDet.TextColor = whitecolor";
mostCurrent._lblepgdet.setTextColor(_vvvvvvvvvv4);
 //BA.debugLineNum = 767;BA.debugLine="svtengah.Panel.AddView(PanEPG, 0, (i*lblEPGDet.H";
_svtengah.getPanel().AddView((android.view.View)(_panepg.getObject()),(int) (0),(int) ((_i*mostCurrent._lblepgdet.getHeight())+(_i*anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (1.5),mostCurrent.activityBA))),_svtengah.getWidth(),mostCurrent._lblepgdet.getHeight());
 //BA.debugLineNum = 768;BA.debugLine="lblEPGDet.Text = mlbFav.GetValueAt(i)";
mostCurrent._lblepgdet.setText(mostCurrent._vvvvvvvvvv2.GetValueAt(_i));
 //BA.debugLineNum = 769;BA.debugLine="lblEPGDet.Tag =  mlbFav.GetKeyAt(i)";
mostCurrent._lblepgdet.setTag(mostCurrent._vvvvvvvvvv2.GetKeyAt(_i));
 //BA.debugLineNum = 770;BA.debugLine="lblEPGDet.Enabled = False";
mostCurrent._lblepgdet.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 771;BA.debugLine="ivEPGDet2.Tag = \"Delete#None\"";
mostCurrent._ivepgdet2.setTag((Object)("Delete#None"));
 //BA.debugLineNum = 773;BA.debugLine="If keyFav.ContainsKey(mlbFav.GetkeyAt(i)) Then";
if (mostCurrent._vvvvvvvvvv3._vvvvvvvvvvvv7(BA.ObjectToString(mostCurrent._vvvvvvvvvv2.GetKeyAt(_i)))) { 
 //BA.debugLineNum = 774;BA.debugLine="ivThumDet.Bitmap = keyFav.GetBitmap(mlbFav.Getk";
mostCurrent._ivthumdet.setBitmap((android.graphics.Bitmap)(mostCurrent._vvvvvvvvvv3._vvvvvvvvvvvvv2(BA.ObjectToString(mostCurrent._vvvvvvvvvv2.GetKeyAt(_i))).getObject()));
 //BA.debugLineNum = 775;BA.debugLine="ivEPGDet.Tag = keyFav.GetSimple(mlbFav.GetkeyAt";
mostCurrent._ivepgdet.setTag((Object)(mostCurrent._vvvvvvvvvv3._vvvvvvvvvvvvv0(BA.ObjectToString(mostCurrent._vvvvvvvvvv2.GetKeyAt(_i))+"#Label")));
 //BA.debugLineNum = 776;BA.debugLine="ivThumDet.Tag = keyFav.GetSimple(mlbFav.GetkeyA";
mostCurrent._ivthumdet.setTag((Object)(mostCurrent._vvvvvvvvvv3._vvvvvvvvvvvvv0(BA.ObjectToString(mostCurrent._vvvvvvvvvv2.GetKeyAt(_i))+"#Label")));
 };
 //BA.debugLineNum = 778;BA.debugLine="svtengah.Panel.Height = (lblEPGDet.Height * mlbF";
_svtengah.getPanel().setHeight((int) ((mostCurrent._lblepgdet.getHeight()*mostCurrent._vvvvvvvvvv2.getSize())+(mostCurrent._vvvvvvvvvv2.getSize()*anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (1.5),mostCurrent.activityBA))+mostCurrent._pbmain.getHeight()));
 }
};
 //BA.debugLineNum = 782;BA.debugLine="GifSmart.Visible = False";
mostCurrent._gifsmart.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 783;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvvvvvvvvvvvvvvvvvvvvvv7() throws Exception{
String _strcmd = "";
ottplaymedia.mncplaymedia.com.mnchttpjob _chnjob = null;
 //BA.debugLineNum = 722;BA.debugLine="Sub GetListLiveTV";
 //BA.debugLineNum = 723;BA.debugLine="Dim strCMD 	As String";
_strcmd = "";
 //BA.debugLineNum = 724;BA.debugLine="Dim ChnJob 	As MNCHttpJob";
_chnjob = new ottplaymedia.mncplaymedia.com.mnchttpjob();
 //BA.debugLineNum = 726;BA.debugLine="strCMD = Fungsi.mpList.Get(\"URLListVOD\")";
_strcmd = BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("URLListVOD")));
 //BA.debugLineNum = 727;BA.debugLine="ChnJob.Initialize(\"GetListLiveTV\", Me)";
_chnjob._initialize(processBA,"GetListLiveTV",utama.getObject());
 //BA.debugLineNum = 729;BA.debugLine="Try";
try { //BA.debugLineNum = 730;BA.debugLine="ChnJob.Download(strCMD)";
_chnjob._download(_strcmd);
 //BA.debugLineNum = 731;BA.debugLine="ChnJob.GetRequest.SetHeader(\"Cache-Control\", \"no";
_chnjob._vvvvvvvvvvvvvvvvvvvvv1().SetHeader("Cache-Control","no-cache, must-revalidate");
 //BA.debugLineNum = 732;BA.debugLine="ChnJob.GetRequest.Timeout = DateTime.TicksPerSec";
_chnjob._vvvvvvvvvvvvvvvvvvvvv1().setTimeout((int) (anywheresoftware.b4a.keywords.Common.DateTime.TicksPerSecond*60));
 //BA.debugLineNum = 733;BA.debugLine="ChnJob.Tag = strCMD";
_chnjob._vvvvvvvvvvvvvvvvvvvvvvv1 = (Object)(_strcmd);
 } 
       catch (Exception e538) {
			processBA.setLastException(e538); //BA.debugLineNum = 735;BA.debugLine="ChnJob.Release";
_chnjob._vvvvvvvvvvvvvvvvvvvvv0();
 //BA.debugLineNum = 736;BA.debugLine="Fungsi.mpList.Put(\"Channel\", \"No Channel View\")";
mostCurrent._vvvvvvvvvvvvvvv0._v5.Put((Object)("Channel"),(Object)("No Channel View"));
 //BA.debugLineNum = 737;BA.debugLine="BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.NoGetList";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv7.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvvv3,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 739;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvvvvvvvvvvvvvvvvvvvvvv0() throws Exception{
String _strcmd = "";
ottplaymedia.mncplaymedia.com.mnchttpjob _chnjob = null;
 //BA.debugLineNum = 667;BA.debugLine="Sub GetListTVOD";
 //BA.debugLineNum = 668;BA.debugLine="Dim strCMD 	As String";
_strcmd = "";
 //BA.debugLineNum = 669;BA.debugLine="Dim ChnJob 	As MNCHttpJob";
_chnjob = new ottplaymedia.mncplaymedia.com.mnchttpjob();
 //BA.debugLineNum = 671;BA.debugLine="strCMD = Fungsi.mpList.Get(\"URLListVOD\")";
_strcmd = BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("URLListVOD")));
 //BA.debugLineNum = 672;BA.debugLine="ChnJob.Initialize(\"GetListTVOD\", Me)";
_chnjob._initialize(processBA,"GetListTVOD",utama.getObject());
 //BA.debugLineNum = 674;BA.debugLine="Try";
try { //BA.debugLineNum = 675;BA.debugLine="ChnJob.Download(strCMD)";
_chnjob._download(_strcmd);
 //BA.debugLineNum = 676;BA.debugLine="ChnJob.GetRequest.SetHeader(\"Cache-Control\", \"no";
_chnjob._vvvvvvvvvvvvvvvvvvvvv1().SetHeader("Cache-Control","no-cache, must-revalidate");
 //BA.debugLineNum = 677;BA.debugLine="ChnJob.GetRequest.Timeout = DateTime.TicksPerSec";
_chnjob._vvvvvvvvvvvvvvvvvvvvv1().setTimeout((int) (anywheresoftware.b4a.keywords.Common.DateTime.TicksPerSecond*60));
 //BA.debugLineNum = 678;BA.debugLine="ChnJob.Tag = strCMD";
_chnjob._vvvvvvvvvvvvvvvvvvvvvvv1 = (Object)(_strcmd);
 } 
       catch (Exception e492) {
			processBA.setLastException(e492); //BA.debugLineNum = 680;BA.debugLine="ChnJob.Release";
_chnjob._vvvvvvvvvvvvvvvvvvvvv0();
 //BA.debugLineNum = 681;BA.debugLine="Fungsi.mpList.Put(\"Channel\", \"No Channel View\")";
mostCurrent._vvvvvvvvvvvvvvv0._v5.Put((Object)("Channel"),(Object)("No Channel View"));
 //BA.debugLineNum = 682;BA.debugLine="BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.NoGetList";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv7.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvvv3,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 684;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvvvvvvvvvvvvvvvvvvvvvvv1() throws Exception{
ottplaymedia.mncplaymedia.com.mnchttpjob _homejob = null;
anywheresoftware.b4a.objects.collections.Map _mpage = null;
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _gen = null;
String _strenc = "";
 //BA.debugLineNum = 849;BA.debugLine="Sub GetListVOD";
 //BA.debugLineNum = 850;BA.debugLine="Dim HomeJob As MNCHttpJob";
_homejob = new ottplaymedia.mncplaymedia.com.mnchttpjob();
 //BA.debugLineNum = 851;BA.debugLine="Dim mPage		As Map";
_mpage = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 852;BA.debugLine="Dim Gen			As JSONGenerator";
_gen = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();
 //BA.debugLineNum = 853;BA.debugLine="Dim	strEnc	As String";
_strenc = "";
 //BA.debugLineNum = 855;BA.debugLine="mPage.Initialize";
_mpage.Initialize();
 //BA.debugLineNum = 856;BA.debugLine="mPage.Clear";
_mpage.Clear();
 //BA.debugLineNum = 857;BA.debugLine="mPage.Put(Fungsi.mpList.Get(\"Variabel\"), \"new-rel";
_mpage.Put(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("Variabel")),(Object)("new-release"));
 //BA.debugLineNum = 859;BA.debugLine="Gen.Initialize(mPage)";
_gen.Initialize(_mpage);
 //BA.debugLineNum = 860;BA.debugLine="HomeJob.Initialize(\"GetListVOD\", Me)";
_homejob._initialize(processBA,"GetListVOD",utama.getObject());
 //BA.debugLineNum = 862;BA.debugLine="Try";
try { //BA.debugLineNum = 863;BA.debugLine="HomeJob.Tag = \"new-release\"";
_homejob._vvvvvvvvvvvvvvvvvvvvvvv1 = (Object)("new-release");
 //BA.debugLineNum = 864;BA.debugLine="strEnc = Fungsi.ProsesEnkripsi(Gen.ToPrettyStrin";
_strenc = mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvvv0(mostCurrent.activityBA,_gen.ToPrettyString((int) (2)));
 //BA.debugLineNum = 865;BA.debugLine="If strEnc = \"Enkripsi Failed\" Then Return";
if ((_strenc).equals("Enkripsi Failed")) { 
if (true) return "";};
 //BA.debugLineNum = 866;BA.debugLine="HomeJob.PostString(Fungsi.mpList.Get(\"SlugURL\"),";
_homejob._vvvvvvvvvvvvvvvvvvvvv7(BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("SlugURL"))),"teks="+_strenc);
 //BA.debugLineNum = 867;BA.debugLine="HomeJob.GetRequest.SetHeader(\"Cache-Control\", \"n";
_homejob._vvvvvvvvvvvvvvvvvvvvv1().SetHeader("Cache-Control","no-cache, must-revalidate");
 //BA.debugLineNum = 868;BA.debugLine="HomeJob.GetRequest.Timeout = DateTime.TicksPerSe";
_homejob._vvvvvvvvvvvvvvvvvvvvv1().setTimeout((int) (anywheresoftware.b4a.keywords.Common.DateTime.TicksPerSecond*60));
 } 
       catch (Exception e647) {
			processBA.setLastException(e647); //BA.debugLineNum = 870;BA.debugLine="BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.CateFail,";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv7.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv4,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 872;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 16;BA.debugLine="Dim		myMap As		Map";
mostCurrent._vvvvvvvvv7 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 17;BA.debugLine="Dim myphone		As Phone";
mostCurrent._vvv4 = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 20;BA.debugLine="Private Spasi			As Int";
_vvvvvvvvvvvvvvvvvvvvvvvv0 = 0;
 //BA.debugLineNum = 21;BA.debugLine="Private Lebar			As Int";
_vvvvvvvvvvvvvvvvvvvvvvvvv1 = 0;
 //BA.debugLineNum = 22;BA.debugLine="Private Tinggi			As Int";
_vvvvvvvvvvvvvvvvvvvvvvvvv2 = 0;
 //BA.debugLineNum = 23;BA.debugLine="Private Durasi			As Int";
_vvvvvvvvvvvvvvvvvvvvvvvvv3 = 0;
 //BA.debugLineNum = 24;BA.debugLine="Private jeday			As Int";
_vvvvvvvvvvvvvvvvvvvvvvvvv4 = 0;
 //BA.debugLineNum = 25;BA.debugLine="Private Device			As Int";
_vvvvvvvvvvvvvvvvvvvvvvvvv5 = 0;
 //BA.debugLineNum = 28;BA.debugLine="Private BDUtama			As BetterDialogs";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv7 = new flm.b4a.betterdialogs.BetterDialogs();
 //BA.debugLineNum = 29;BA.debugLine="Private pbMain 			As Panel";
mostCurrent._pbmain = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private MNCJob 			As MNCHttpJob";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvv3 = new ottplaymedia.mncplaymedia.com.mnchttpjob();
 //BA.debugLineNum = 31;BA.debugLine="Private lblFeat 		As Label";
mostCurrent._lblfeat = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private GifSmart 		As MovieViewControl";
mostCurrent._gifsmart = new com.datasteam.b4a.xtraviews.MovieViewControl();
 //BA.debugLineNum = 33;BA.debugLine="Private paMain 			As Panel";
mostCurrent._pamain = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private pTengah 		As Panel";
mostCurrent._ptengah = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private cvThumDet 		As MovieViewControl";
mostCurrent._cvthumdet = new com.datasteam.b4a.xtraviews.MovieViewControl();
 //BA.debugLineNum = 36;BA.debugLine="Private picture			As Map";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvv2 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 39;BA.debugLine="Private MapTampung		As Map";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvv1 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 43;BA.debugLine="Private ups 				As Intent";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv4 = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Private ipack				As Int";
_vvvvvvvvvvvvvvvvvvvvvvvv5 = 0;
 //BA.debugLineNum = 45;BA.debugLine="Private pm 					As PackageManager";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv6 = new anywheresoftware.b4a.phone.PackageManagerWrapper();
 //BA.debugLineNum = 49;BA.debugLine="Private ivEPGDet	 	As ImageView";
mostCurrent._ivepgdet = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 50;BA.debugLine="Private ivEPGDet2		As ImageView";
mostCurrent._ivepgdet2 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 51;BA.debugLine="Private ivEPGDet3		As ImageView";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvv2 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 52;BA.debugLine="Private lblEPGDet 		As Label";
mostCurrent._lblepgdet = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 53;BA.debugLine="Private ivThumDet 		As ImageView";
mostCurrent._ivthumdet = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 54;BA.debugLine="Private bmpTemp			As ImageView";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvv6 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 55;BA.debugLine="Private bmpFav			As ImageView";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvv7 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 56;BA.debugLine="Private bmpDelFav		As ImageView";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvv0 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 60;BA.debugLine="Private ivLiveTV 		As ImageView";
mostCurrent._ivlivetv = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 61;BA.debugLine="Private ivTVOD 			As ImageView";
mostCurrent._ivtvod = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 62;BA.debugLine="Private ivVOD 			As ImageView";
mostCurrent._ivvod = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 63;BA.debugLine="Private ivFav 			As ImageView";
mostCurrent._ivfav = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 64;BA.debugLine="Private lblGenre 		As Label";
mostCurrent._lblgenre = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 68;BA.debugLine="Dim 	User			As Map";
mostCurrent._vvvvvvvvv0 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 69;BA.debugLine="Dim		Fav				As Map";
mostCurrent._vvvvvvvvvv1 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 70;BA.debugLine="Dim 	RAF				As RandomAccessFile";
mostCurrent._vv1 = new anywheresoftware.b4a.randomaccessfile.RandomAccessFile();
 //BA.debugLineNum = 73;BA.debugLine="Dim		mlbFav			As Map";
mostCurrent._vvvvvvvvvv2 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 74;BA.debugLine="Dim		keyFav			As KeyValueStore";
mostCurrent._vvvvvvvvvv3 = new ottplaymedia.mncplaymedia.com.keyvaluestore();
 //BA.debugLineNum = 75;BA.debugLine="Dim		whitecolor		As Int = Colors.White";
_vvvvvvvvvv4 = anywheresoftware.b4a.keywords.Common.Colors.White;
 //BA.debugLineNum = 76;BA.debugLine="End Sub";
return "";
}
public static String  _init_layout() throws Exception{
 //BA.debugLineNum = 203;BA.debugLine="Sub init_layout";
 //BA.debugLineNum = 204;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 //BA.debugLineNum = 206;BA.debugLine="lblGenre.Background = Fungsi.GetDrawable(\"mnc_top";
mostCurrent._lblgenre.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"mnc_top")));
 //BA.debugLineNum = 208;BA.debugLine="Select Fungsi.DataConfig.sTab";
switch (BA.switchObjectToInt(mostCurrent._vvvvvvvvvvvvvvv0._v0.sTab,"LiveTV","TVOD","VOD","Favorite")) {
case 0:
 //BA.debugLineNum = 210;BA.debugLine="lblFeat.Background = Fungsi.GetDrawable(\"titleli";
mostCurrent._lblfeat.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"titlelivetv")));
 //BA.debugLineNum = 211;BA.debugLine="ivLiveTV.Background = Fungsi.GetDrawable(\"livetv";
mostCurrent._ivlivetv.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"livetvwhite")));
 //BA.debugLineNum = 212;BA.debugLine="ivTVOD.Background = Fungsi.GetDrawable(\"tvod\")";
mostCurrent._ivtvod.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"tvod")));
 //BA.debugLineNum = 213;BA.debugLine="ivVOD.Background = Fungsi.GetDrawable(\"vod\")";
mostCurrent._ivvod.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"vod")));
 //BA.debugLineNum = 214;BA.debugLine="ivFav.Background = Fungsi.GetDrawable(\"favoriteb";
mostCurrent._ivfav.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"favoriteblack")));
 break;
case 1:
 //BA.debugLineNum = 216;BA.debugLine="lblFeat.Background = Fungsi.GetDrawable(\"titletv";
mostCurrent._lblfeat.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"titletvod")));
 //BA.debugLineNum = 217;BA.debugLine="ivTVOD.Background = Fungsi.GetDrawable(\"tvodwhit";
mostCurrent._ivtvod.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"tvodwhite")));
 //BA.debugLineNum = 218;BA.debugLine="ivLiveTV.Background = Fungsi.GetDrawable(\"livetv";
mostCurrent._ivlivetv.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"livetv")));
 //BA.debugLineNum = 219;BA.debugLine="ivVOD.Background = Fungsi.GetDrawable(\"vod\")";
mostCurrent._ivvod.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"vod")));
 //BA.debugLineNum = 220;BA.debugLine="ivFav.Background = Fungsi.GetDrawable(\"favoriteb";
mostCurrent._ivfav.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"favoriteblack")));
 break;
case 2:
 //BA.debugLineNum = 222;BA.debugLine="lblFeat.Background = Fungsi.GetDrawable(\"titlevo";
mostCurrent._lblfeat.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"titlevod")));
 //BA.debugLineNum = 223;BA.debugLine="ivTVOD.Background = Fungsi.GetDrawable(\"tvod\")";
mostCurrent._ivtvod.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"tvod")));
 //BA.debugLineNum = 224;BA.debugLine="ivLiveTV.Background = Fungsi.GetDrawable(\"livetv";
mostCurrent._ivlivetv.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"livetv")));
 //BA.debugLineNum = 225;BA.debugLine="ivVOD.Background = Fungsi.GetDrawable(\"vodwhite\"";
mostCurrent._ivvod.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"vodwhite")));
 //BA.debugLineNum = 226;BA.debugLine="ivFav.Background = Fungsi.GetDrawable(\"favoriteb";
mostCurrent._ivfav.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"favoriteblack")));
 break;
case 3:
 //BA.debugLineNum = 228;BA.debugLine="lblFeat.Background = Fungsi.GetDrawable(\"titlefa";
mostCurrent._lblfeat.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"titlefav")));
 //BA.debugLineNum = 229;BA.debugLine="ivTVOD.Background = Fungsi.GetDrawable(\"tvod\")";
mostCurrent._ivtvod.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"tvod")));
 //BA.debugLineNum = 230;BA.debugLine="ivLiveTV.Background = Fungsi.GetDrawable(\"livetv";
mostCurrent._ivlivetv.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"livetv")));
 //BA.debugLineNum = 231;BA.debugLine="ivVOD.Background = Fungsi.GetDrawable(\"vod\")";
mostCurrent._ivvod.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"vod")));
 //BA.debugLineNum = 232;BA.debugLine="ivFav.Background = Fungsi.GetDrawable(\"favoritew";
mostCurrent._ivfav.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"favoritewhite")));
 break;
}
;
 //BA.debugLineNum = 235;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 //BA.debugLineNum = 236;BA.debugLine="End Sub";
return "";
}
public static String  _ivfav_click() throws Exception{
 //BA.debugLineNum = 251;BA.debugLine="Sub ivFav_Click";
 //BA.debugLineNum = 252;BA.debugLine="If Fungsi.DataConfig.sTab <> \"Favorite\" Then";
if ((mostCurrent._vvvvvvvvvvvvvvv0._v0.sTab).equals("Favorite") == false) { 
 //BA.debugLineNum = 253;BA.debugLine="pTengah.RemoveAllViews";
mostCurrent._ptengah.RemoveAllViews();
 //BA.debugLineNum = 254;BA.debugLine="CallSub(ImageDownloader, \"ActivityIsPaused\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()),"ActivityIsPaused");
 //BA.debugLineNum = 257;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 //BA.debugLineNum = 258;BA.debugLine="Fungsi.DataConfig.sTab = \"Favorite\"";
mostCurrent._vvvvvvvvvvvvvvv0._v0.sTab = "Favorite";
 //BA.debugLineNum = 259;BA.debugLine="Fungsi.TulisFileConfig(Fungsi.DataConfig)";
mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvvvv4(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._v0);
 //BA.debugLineNum = 260;BA.debugLine="init_layout";
_init_layout();
 //BA.debugLineNum = 261;BA.debugLine="Cek_Koneksi";
_cek_koneksi();
 };
 //BA.debugLineNum = 264;BA.debugLine="End Sub";
return "";
}
public static String  _ivlivetv_click() throws Exception{
 //BA.debugLineNum = 238;BA.debugLine="Sub ivLiveTV_Click";
 //BA.debugLineNum = 239;BA.debugLine="If Fungsi.DataConfig.sTab <> \"LiveTV\" Then";
if ((mostCurrent._vvvvvvvvvvvvvvv0._v0.sTab).equals("LiveTV") == false) { 
 //BA.debugLineNum = 240;BA.debugLine="pTengah.RemoveAllViews";
mostCurrent._ptengah.RemoveAllViews();
 //BA.debugLineNum = 241;BA.debugLine="CallSub(ImageDownloader, \"ActivityIsPaused\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()),"ActivityIsPaused");
 //BA.debugLineNum = 243;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 //BA.debugLineNum = 244;BA.debugLine="Fungsi.DataConfig.sTab = \"LiveTV\"";
mostCurrent._vvvvvvvvvvvvvvv0._v0.sTab = "LiveTV";
 //BA.debugLineNum = 245;BA.debugLine="Fungsi.tulisfileconfig(Fungsi.DataConfig)";
mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvvvv4(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._v0);
 //BA.debugLineNum = 246;BA.debugLine="init_layout";
_init_layout();
 //BA.debugLineNum = 247;BA.debugLine="Cek_Koneksi";
_cek_koneksi();
 };
 //BA.debugLineNum = 249;BA.debugLine="End Sub";
return "";
}
public static String  _ivtvod_click() throws Exception{
 //BA.debugLineNum = 632;BA.debugLine="Sub ivTVOD_Click";
 //BA.debugLineNum = 633;BA.debugLine="If Fungsi.DataConfig.sTab <> \"TVOD\" Then pTengah.";
if ((mostCurrent._vvvvvvvvvvvvvvv0._v0.sTab).equals("TVOD") == false) { 
mostCurrent._ptengah.RemoveAllViews();};
 //BA.debugLineNum = 635;BA.debugLine="CallSub(ImageDownloader, \"ActivityIsPaused\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()),"ActivityIsPaused");
 //BA.debugLineNum = 637;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 //BA.debugLineNum = 638;BA.debugLine="Fungsi.DataConfig.sTab = \"TVOD\"";
mostCurrent._vvvvvvvvvvvvvvv0._v0.sTab = "TVOD";
 //BA.debugLineNum = 639;BA.debugLine="Fungsi.tulisfileconfig(Fungsi.DataConfig)";
mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvvvv4(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._v0);
 //BA.debugLineNum = 640;BA.debugLine="init_layout";
_init_layout();
 //BA.debugLineNum = 641;BA.debugLine="Cek_Koneksi";
_cek_koneksi();
 //BA.debugLineNum = 642;BA.debugLine="End Sub";
return "";
}
public static String  _ivvod_click() throws Exception{
 //BA.debugLineNum = 837;BA.debugLine="Sub ivVOD_Click";
 //BA.debugLineNum = 838;BA.debugLine="If Fungsi.DataConfig.sTab <> \"VOD\" Then pTengah.R";
if ((mostCurrent._vvvvvvvvvvvvvvv0._v0.sTab).equals("VOD") == false) { 
mostCurrent._ptengah.RemoveAllViews();};
 //BA.debugLineNum = 840;BA.debugLine="CallSub(ImageDownloader, \"ActivityIsPaused\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()),"ActivityIsPaused");
 //BA.debugLineNum = 842;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 //BA.debugLineNum = 843;BA.debugLine="Fungsi.DataConfig.sTab = \"VOD\"";
mostCurrent._vvvvvvvvvvvvvvv0._v0.sTab = "VOD";
 //BA.debugLineNum = 844;BA.debugLine="Fungsi.tulisfileconfig(Fungsi.DataConfig)";
mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvvvv4(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._v0);
 //BA.debugLineNum = 845;BA.debugLine="init_layout";
_init_layout();
 //BA.debugLineNum = 846;BA.debugLine="Cek_Koneksi";
_cek_koneksi();
 //BA.debugLineNum = 847;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(ottplaymedia.mncplaymedia.com.mnchttpjob _mnchttp) throws Exception{
adr.stringfunctions.stringfunctions _sf = null;
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
anywheresoftware.b4a.objects.collections.Map _mresult = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.List _listvod = null;
anywheresoftware.b4a.objects.collections.List _picvod = null;
String _stmp = "";
byte[] _btmp = null;
anywheresoftware.b4a.agraham.byteconverter.ByteConverter _bctmp = null;
anywheresoftware.b4a.objects.StringUtils _su = null;
it.giuseppe.salvi.gridview.library.core.GridViewActivityWrapper _pgvutama = null;
anywheresoftware.b4a.objects.ScrollViewWrapper _svtengah = null;
anywheresoftware.b4a.objects.collections.Map _mapimage = null;
anywheresoftware.b4a.objects.collections.Map _mapepg = null;
anywheresoftware.b4a.objects.collections.List _lstchacode = null;
String _strcolcode = "";
String _cekscreen = "";
anywheresoftware.b4a.objects.PanelWrapper _panepg = null;
anywheresoftware.b4a.objects.collections.Map _new = null;
anywheresoftware.b4a.objects.collections.List _top = null;
String _subkiri = "";
int _k1 = 0;
int _k2 = 0;
anywheresoftware.b4a.objects.collections.Map _contentvideo = null;
anywheresoftware.b4a.objects.collections.Map _colkiri = null;
int _x2 = 0;
 //BA.debugLineNum = 292;BA.debugLine="Sub JobDone(MNChttp As MNCHttpJob)";
 //BA.debugLineNum = 293;BA.debugLine="Dim sf				As StringFunctions";
_sf = new adr.stringfunctions.stringfunctions();
 //BA.debugLineNum = 294;BA.debugLine="Dim parser 		As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 295;BA.debugLine="Dim mresult		As Map";
_mresult = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 296;BA.debugLine="Dim i 				As Int";
_i = 0;
 //BA.debugLineNum = 297;BA.debugLine="Dim ListVOD		As List";
_listvod = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 298;BA.debugLine="Dim PicVOD		As List";
_picvod = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 300;BA.debugLine="Dim sTmp 			As String";
_stmp = "";
 //BA.debugLineNum = 301;BA.debugLine="Dim bTmp()		As Byte";
_btmp = new byte[(int) (0)];
;
 //BA.debugLineNum = 302;BA.debugLine="Dim bcTmp			As ByteConverter";
_bctmp = new anywheresoftware.b4a.agraham.byteconverter.ByteConverter();
 //BA.debugLineNum = 303;BA.debugLine="Dim su 				As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 305;BA.debugLine="StopService(MNCUtils2Service)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvvv1.getObject()));
 //BA.debugLineNum = 306;BA.debugLine="CancelScheduledService(MNCUtils2Service)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvvv1.getObject()));
 //BA.debugLineNum = 307;BA.debugLine="StopService(ImageDownloader)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()));
 //BA.debugLineNum = 308;BA.debugLine="CancelScheduledService(ImageDownloader)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()));
 //BA.debugLineNum = 309;BA.debugLine="StopService(EPGDownloader)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()));
 //BA.debugLineNum = 310;BA.debugLine="CancelScheduledService(EPGDownloader)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()));
 //BA.debugLineNum = 313;BA.debugLine="If MNChttp.Success = False Then";
if (_mnchttp._vvvvvvvvvvvvvvvvvvvvvv2==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 314;BA.debugLine="If (sf.InString(MNChttp.ErrorMessage, \"timed out";
if ((_sf._vvv4(_mnchttp._vvvvvvvvvvvvvvvvvvvvvv5,"timed out")>0) || (_sf._vvv4(_mnchttp._vvvvvvvvvvvvvvvvvvvvvv5,"refused")>0) || (_sf._vvv4(_mnchttp._vvvvvvvvvvvvvvvvvvvvvv5,"Unable to resolve host")>0) || (_sf._vvv4(_mnchttp._vvvvvvvvvvvvvvvvvvvvvv5,"UnknownHostException")>0) || (_sf._vvv4(_mnchttp._vvvvvvvvvvvvvvvvvvvvvv5,"FileNotFound")>0)) { 
 //BA.debugLineNum = 317;BA.debugLine="GifSmart.Visible = False";
mostCurrent._gifsmart.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 318;BA.debugLine="BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.NoKoneks";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv7.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvv6,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 320;BA.debugLine="If sf.InString(MNChttp.ErrorMessage, \"Not Found";
if (_sf._vvv4(_mnchttp._vvvvvvvvvvvvvvvvvvvvvv5,"Not Found")<0) { 
 //BA.debugLineNum = 321;BA.debugLine="GifSmart.Visible = False";
mostCurrent._gifsmart.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 322;BA.debugLine="BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.Unknown";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv7.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvv0,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 324;BA.debugLine="If MNChttp.JobName = \"Koneksi\" Then";
if ((_mnchttp._vvvvvvvvvvvvvvvvvvvvvv1).equals("Koneksi")) { 
 //BA.debugLineNum = 325;BA.debugLine="MNChttp.Release";
_mnchttp._vvvvvvvvvvvvvvvvvvvvv0();
 };
 };
 };
 //BA.debugLineNum = 331;BA.debugLine="Fungsi.mpList.Put(\"Koneksi\", \"Offline\")";
mostCurrent._vvvvvvvvvvvvvvv0._v5.Put((Object)("Koneksi"),(Object)("Offline"));
 //BA.debugLineNum = 332;BA.debugLine="MNChttp.Release";
_mnchttp._vvvvvvvvvvvvvvvvvvvvv0();
 }else {
 //BA.debugLineNum = 336;BA.debugLine="Fungsi.mpList.Put(\"Koneksi\", \"Online\")";
mostCurrent._vvvvvvvvvvvvvvv0._v5.Put((Object)("Koneksi"),(Object)("Online"));
 //BA.debugLineNum = 338;BA.debugLine="Select MNChttp.JobName";
switch (BA.switchObjectToInt(_mnchttp._vvvvvvvvvvvvvvvvvvvvvv1,"Koneksi","GetListTVOD","GetListLiveTV","GetURLLiveTV","GetListVOD")) {
case 0:
 //BA.debugLineNum = 341;BA.debugLine="MNChttp.Release";
_mnchttp._vvvvvvvvvvvvvvvvvvvvv0();
 //BA.debugLineNum = 343;BA.debugLine="Select Fungsi.DataConfig.sTab";
switch (BA.switchObjectToInt(mostCurrent._vvvvvvvvvvvvvvv0._v0.sTab,"TVOD","LiveTV","VOD","Favorite")) {
case 0:
 //BA.debugLineNum = 345;BA.debugLine="GetListTVOD";
_vvvvvvvvvvvvvvvvvvvvvvvvvv0();
 break;
case 1:
 //BA.debugLineNum = 347;BA.debugLine="GetListLiveTV";
_vvvvvvvvvvvvvvvvvvvvvvvvvv7();
 break;
case 2:
 //BA.debugLineNum = 349;BA.debugLine="GetListVOD";
_vvvvvvvvvvvvvvvvvvvvvvvvvvv1();
 break;
case 3:
 //BA.debugLineNum = 351;BA.debugLine="GetListFavorite";
_vvvvvvvvvvvvvvvvvvvvvvvvvv5();
 break;
}
;
 break;
case 1:
case 2:
 //BA.debugLineNum = 358;BA.debugLine="Try";
try { //BA.debugLineNum = 359;BA.debugLine="parser.Initialize(MNChttp.GetString)";
_parser.Initialize(_mnchttp._vvvvvvvvvvvvvvvvvvvvv2());
 //BA.debugLineNum = 360;BA.debugLine="MNChttp.Release";
_mnchttp._vvvvvvvvvvvvvvvvvvvvv0();
 } 
       catch (Exception e259) {
			processBA.setLastException(e259); //BA.debugLineNum = 362;BA.debugLine="GifSmart.Visible = False";
mostCurrent._gifsmart.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 363;BA.debugLine="BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.FailJS";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv7.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvvv4,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 //BA.debugLineNum = 364;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 367;BA.debugLine="Try";
try { //BA.debugLineNum = 368;BA.debugLine="mresult.Initialize";
_mresult.Initialize();
 //BA.debugLineNum = 369;BA.debugLine="mresult = parser.NextObject";
_mresult = _parser.NextObject();
 } 
       catch (Exception e267) {
			processBA.setLastException(e267); //BA.debugLineNum = 371;BA.debugLine="GifSmart.Visible = False";
mostCurrent._gifsmart.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 372;BA.debugLine="BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.FailPr";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv7.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvvv5,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 //BA.debugLineNum = 373;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 377;BA.debugLine="If mresult.Get(\"returncode\") <> \"0\" Then";
if ((_mresult.Get((Object)("returncode"))).equals((Object)("0")) == false) { 
 //BA.debugLineNum = 379;BA.debugLine="GifSmart.Visible = False";
mostCurrent._gifsmart.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 380;BA.debugLine="BDUtama.Msgbox(Fungsi.TitleBox, mresult.Get(\"";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv7.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,BA.ObjectToString(_mresult.Get((Object)("errormsg"))),mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 384;BA.debugLine="pTengah.RemoveAllViews";
mostCurrent._ptengah.RemoveAllViews();
 //BA.debugLineNum = 386;BA.debugLine="ListVOD = mresult.Get(\"channelname\")";
_listvod.setObject((java.util.List)(_mresult.Get((Object)("channelname"))));
 //BA.debugLineNum = 387;BA.debugLine="PicVOD 	= mresult.Get(\"filename\")";
_picvod.setObject((java.util.List)(_mresult.Get((Object)("filename"))));
 //BA.debugLineNum = 389;BA.debugLine="Select MNChttp.JobName";
switch (BA.switchObjectToInt(_mnchttp._vvvvvvvvvvvvvvvvvvvvvv1,"GetListTVOD","GetListLiveTV")) {
case 0:
 //BA.debugLineNum = 394;BA.debugLine="Dim pgvUtama As PhotoGridView";
_pgvutama = new it.giuseppe.salvi.gridview.library.core.GridViewActivityWrapper();
 //BA.debugLineNum = 396;BA.debugLine="pgvUtama.Initialize(\"pgvEvent\")";
_pgvutama.Initialize(mostCurrent.activityBA,"pgvEvent");
 //BA.debugLineNum = 397;BA.debugLine="pgvUtama.CacheInMemory = True";
_pgvutama.setCacheInMemory(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 398;BA.debugLine="pgvUtama.CacheOnDisk = True";
_pgvutama.setCacheOnDisk(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 399;BA.debugLine="pgvUtama.CompressQuality = 90";
_pgvutama.setCompressQuality((int) (90));
 //BA.debugLineNum = 400;BA.debugLine="pgvUtama.RoundedBitmap = False";
_pgvutama.setRoundedBitmap(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 401;BA.debugLine="pgvUtama.Gravity=Gravity.CENTER";
_pgvutama.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 402;BA.debugLine="pgvUtama.Enabled = True";
_pgvutama.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 403;BA.debugLine="pgvUtama.ProgressBarIndeterminate = False";
_pgvutama.setProgressBarIndeterminate(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 404;BA.debugLine="pgvUtama.ProgressBarVisible = True";
_pgvutama.setProgressBarVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 405;BA.debugLine="pgvUtama.ScaleType = pgvUtama.ScaleType.Fit_";
_pgvutama.setScaleType(_pgvutama.getScaleType().Fit_Center);
 //BA.debugLineNum = 407;BA.debugLine="If Device = 0 Then";
if (_vvvvvvvvvvvvvvvvvvvvvvvvv5==0) { 
 //BA.debugLineNum = 408;BA.debugLine="If Activity.Width > Activity.Height Then";
if (mostCurrent._activity.getWidth()>mostCurrent._activity.getHeight()) { 
 //BA.debugLineNum = 409;BA.debugLine="pgvUtama.NumColumns = 4";
_pgvutama.setNumColumns((int) (4));
 }else {
 //BA.debugLineNum = 411;BA.debugLine="pgvUtama.NumColumns = 3";
_pgvutama.setNumColumns((int) (3));
 };
 //BA.debugLineNum = 414;BA.debugLine="pgvUtama.HorizontalSpacing = 3%x";
_pgvutama.setHorizontalSpacing(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (3),mostCurrent.activityBA));
 //BA.debugLineNum = 415;BA.debugLine="pgvUtama.VerticalSpacing = 0.5%y";
_pgvutama.setVerticalSpacing(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (0.5),mostCurrent.activityBA));
 //BA.debugLineNum = 416;BA.debugLine="pgvUtama.ItemWidth = 40%x";
_pgvutama.setItemWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (40),mostCurrent.activityBA));
 //BA.debugLineNum = 417;BA.debugLine="pgvUtama.ItemHeight = 120%y";
_pgvutama.setItemHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (120),mostCurrent.activityBA));
 }else {
 //BA.debugLineNum = 419;BA.debugLine="If Activity.Width > Activity.Height Then";
if (mostCurrent._activity.getWidth()>mostCurrent._activity.getHeight()) { 
 //BA.debugLineNum = 420;BA.debugLine="pgvUtama.NumColumns = 5";
_pgvutama.setNumColumns((int) (5));
 }else {
 //BA.debugLineNum = 422;BA.debugLine="pgvUtama.NumColumns = 4";
_pgvutama.setNumColumns((int) (4));
 };
 //BA.debugLineNum = 425;BA.debugLine="pgvUtama.HorizontalSpacing = 3%x";
_pgvutama.setHorizontalSpacing(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (3),mostCurrent.activityBA));
 //BA.debugLineNum = 426;BA.debugLine="pgvUtama.VerticalSpacing = 0.5%y";
_pgvutama.setVerticalSpacing(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (0.5),mostCurrent.activityBA));
 //BA.debugLineNum = 427;BA.debugLine="pgvUtama.ItemWidth = 40%x";
_pgvutama.setItemWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (40),mostCurrent.activityBA));
 //BA.debugLineNum = 428;BA.debugLine="pgvUtama.ItemHeight = 120%y";
_pgvutama.setItemHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (120),mostCurrent.activityBA));
 };
 //BA.debugLineNum = 432;BA.debugLine="MapTampung.Clear";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvv1.Clear();
 //BA.debugLineNum = 433;BA.debugLine="MapTampung.Put(\"channelname\", mresult.Get(\"c";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvv1.Put((Object)("channelname"),_mresult.Get((Object)("channelname")));
 //BA.debugLineNum = 434;BA.debugLine="MapTampung.Put(\"channelcode\", mresult.Get(\"c";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvv1.Put((Object)("channelcode"),_mresult.Get((Object)("channelcode")));
 //BA.debugLineNum = 435;BA.debugLine="MapTampung.Put(\"columncode\", \"0000\")";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvv1.Put((Object)("columncode"),(Object)("0000"));
 //BA.debugLineNum = 436;BA.debugLine="MapTampung.Put(\"pgvUtama\", pgvUtama)";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvv1.Put((Object)("pgvUtama"),(Object)(_pgvutama.getObject()));
 //BA.debugLineNum = 438;BA.debugLine="For i=0 To ListVOD.Size-1";
{
final int step317 = 1;
final int limit317 = (int) (_listvod.getSize()-1);
for (_i = (int) (0); (step317 > 0 && _i <= limit317) || (step317 < 0 && _i >= limit317); _i = ((int)(0 + _i + step317))) {
 //BA.debugLineNum = 439;BA.debugLine="pgvUtama.AddImageFromWeb(Fungsi.mpList.Get(";
_pgvutama.AddImageFromWeb(BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("PicListVOD")))+BA.ObjectToString(_picvod.Get(_i)));
 }
};
 //BA.debugLineNum = 442;BA.debugLine="pTengah.AddView(pgvUtama, 2%x, 0, pTengah.Wi";
mostCurrent._ptengah.AddView((android.view.View)(_pgvutama.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (2),mostCurrent.activityBA),(int) (0),(int) (mostCurrent._ptengah.getWidth()-anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (4),mostCurrent.activityBA)),(int) (mostCurrent._ptengah.getHeight()-mostCurrent._pbmain.getHeight()));
 break;
case 1:
 //BA.debugLineNum = 446;BA.debugLine="Dim svTengah 		As ScrollView";
_svtengah = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 447;BA.debugLine="Dim mapImage		As Map";
_mapimage = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 448;BA.debugLine="Dim mapEPG			As Map";
_mapepg = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 449;BA.debugLine="Dim lstchacode	As List";
_lstchacode = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 450;BA.debugLine="Dim strcolcode	As String";
_strcolcode = "";
 //BA.debugLineNum = 451;BA.debugLine="Dim cekscreen	As String";
_cekscreen = "";
 //BA.debugLineNum = 454;BA.debugLine="mapImage.Initialize";
_mapimage.Initialize();
 //BA.debugLineNum = 455;BA.debugLine="mapImage.Clear";
_mapimage.Clear();
 //BA.debugLineNum = 456;BA.debugLine="mapEPG.Initialize";
_mapepg.Initialize();
 //BA.debugLineNum = 457;BA.debugLine="mapEPG.Clear";
_mapepg.Clear();
 //BA.debugLineNum = 459;BA.debugLine="svTengah.Initialize(0)";
_svtengah.Initialize(mostCurrent.activityBA,(int) (0));
 //BA.debugLineNum = 460;BA.debugLine="svTengah.Panel.RemoveAllViews";
_svtengah.getPanel().RemoveAllViews();
 //BA.debugLineNum = 461;BA.debugLine="pTengah.AddView(svTengah, 0, 1.5%y, pTengah.";
mostCurrent._ptengah.AddView((android.view.View)(_svtengah.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (1.5),mostCurrent.activityBA),mostCurrent._ptengah.getWidth(),(int) (mostCurrent._ptengah.getHeight()-anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (3),mostCurrent.activityBA)));
 //BA.debugLineNum = 463;BA.debugLine="lstchacode = mresult.Get(\"channelcode\")";
_lstchacode.setObject((java.util.List)(_mresult.Get((Object)("channelcode"))));
 //BA.debugLineNum = 464;BA.debugLine="strcolcode = \"0000\"";
_strcolcode = "0000";
 //BA.debugLineNum = 466;BA.debugLine="For i=0 To ListVOD.Size-1";
{
final int step337 = 1;
final int limit337 = (int) (_listvod.getSize()-1);
for (_i = (int) (0); (step337 > 0 && _i <= limit337) || (step337 < 0 && _i >= limit337); _i = ((int)(0 + _i + step337))) {
 //BA.debugLineNum = 467;BA.debugLine="Dim PanEPG 				As Panel";
_panepg = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 469;BA.debugLine="PanEPG.Initialize(\"\")";
_panepg.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 471;BA.debugLine="If Device = 0 Then";
if (_vvvvvvvvvvvvvvvvvvvvvvvvv5==0) { 
 //BA.debugLineNum = 472;BA.debugLine="PanEPG.LoadLayout(\"LiveTVDetSmart\")";
_panepg.LoadLayout("LiveTVDetSmart",mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 474;BA.debugLine="PanEPG.LoadLayout(\"LiveTVDetail\")";
_panepg.LoadLayout("LiveTVDetail",mostCurrent.activityBA);
 };
 //BA.debugLineNum = 477;BA.debugLine="svTengah.Panel.AddView(PanEPG, 0, (i*lblEPG";
_svtengah.getPanel().AddView((android.view.View)(_panepg.getObject()),(int) (0),(int) ((_i*mostCurrent._lblepgdet.getHeight())+(_i*anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (1.5),mostCurrent.activityBA))),_svtengah.getWidth(),mostCurrent._lblepgdet.getHeight());
 //BA.debugLineNum = 479;BA.debugLine="If (i Mod 30) = 0 Then DoEvents";
if ((_i%30)==0) { 
anywheresoftware.b4a.keywords.Common.DoEvents();};
 //BA.debugLineNum = 481;BA.debugLine="ivEPGDet.Background = bmpTemp.Background";
mostCurrent._ivepgdet.setBackground(mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvv6.getBackground());
 //BA.debugLineNum = 482;BA.debugLine="ivEPGDet2.Background = bmpFav.Background";
mostCurrent._ivepgdet2.setBackground(mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvv7.getBackground());
 //BA.debugLineNum = 485;BA.debugLine="ivEPGDet.Tag = Fungsi.mpList.Get(\"URLLiveTV";
mostCurrent._ivepgdet.setTag((Object)(BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("URLLiveTV")))+BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("LiveTVchancode")))+BA.ObjectToString(_lstchacode.Get(_i))+"&"+BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("LiveTVcolcode")))+_strcolcode+"#Detail"));
 //BA.debugLineNum = 486;BA.debugLine="ivEPGDet2.Tag = \"Putih#None\"";
mostCurrent._ivepgdet2.setTag((Object)("Putih#None"));
 //BA.debugLineNum = 487;BA.debugLine="ivThumDet.Tag = Fungsi.mpList.Get(\"URLLiveT";
mostCurrent._ivthumdet.setTag((Object)(BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("URLLiveTV")))+BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("LiveTVchancode")))+BA.ObjectToString(_lstchacode.Get(_i))+"&"+BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("LiveTVcolcode")))+_strcolcode+"#Thumb#"+BA.ObjectToString(_lstchacode.Get(_i))+"#"+_strcolcode));
 //BA.debugLineNum = 488;BA.debugLine="lblEPGDet.Tag = lstchacode.Get(i) & \"#\" & s";
mostCurrent._lblepgdet.setTag((Object)(BA.ObjectToString(_lstchacode.Get(_i))+"#"+_strcolcode));
 //BA.debugLineNum = 490;BA.debugLine="mapImage.Put(ivThumDet, Fungsi.mpList.Get(\"";
_mapimage.Put((Object)(mostCurrent._ivthumdet.getObject()),(Object)(BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("PicListVOD")))+_sf._vvvv5(BA.ObjectToString(_picvod.Get(_i)),(int) (4),(int) (_sf._vvv7(BA.ObjectToString(_picvod.Get(_i)))-3))));
 //BA.debugLineNum = 491;BA.debugLine="mapEPG.Put(lblEPGDet, Fungsi.mpList.Get(\"EP";
_mapepg.Put((Object)(mostCurrent._lblepgdet.getObject()),(Object)(BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("EPGHour")))+BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("Hourchancode")))+BA.ObjectToString(_lstchacode.Get(_i))+"&"+BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("Hourcolcode")))+_strcolcode));
 }
};
 //BA.debugLineNum = 498;BA.debugLine="CallSubDelayed2(ImageDownloader, \"Download\",";
anywheresoftware.b4a.keywords.Common.CallSubDelayed2(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()),"Download",(Object)(_mapimage));
 //BA.debugLineNum = 499;BA.debugLine="CallSubDelayed2(EPGDownloader, \"Download\", m";
anywheresoftware.b4a.keywords.Common.CallSubDelayed2(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()),"Download",(Object)(_mapepg));
 //BA.debugLineNum = 500;BA.debugLine="svTengah.Panel.Height = (lblEPGDet.Height *";
_svtengah.getPanel().setHeight((int) ((mostCurrent._lblepgdet.getHeight()*_listvod.getSize())+(_listvod.getSize()*anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (1.5),mostCurrent.activityBA))+mostCurrent._pbmain.getHeight()));
 break;
}
;
 //BA.debugLineNum = 506;BA.debugLine="GifSmart.Visible = False";
mostCurrent._gifsmart.setVisible(anywheresoftware.b4a.keywords.Common.False);
 };
 break;
case 3:
 //BA.debugLineNum = 512;BA.debugLine="GifSmart.Visible = False";
mostCurrent._gifsmart.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 514;BA.debugLine="If ipack < 0 Then";
if (_vvvvvvvvvvvvvvvvvvvvvvvv5<0) { 
 //BA.debugLineNum = 515;BA.debugLine="BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.NoPlay";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv7.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvvv0,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 517;BA.debugLine="ups.PutExtra(\"LinkURL\", MNChttp.GetString)";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv4.PutExtra("LinkURL",(Object)(_mnchttp._vvvvvvvvvvvvvvvvvvvvv2()));
 //BA.debugLineNum = 518;BA.debugLine="If ups.IsInitialized Then StartActivity(ups)";
if (mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv4.IsInitialized()) { 
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv4.getObject()));};
 };
 break;
case 4:
 //BA.debugLineNum = 524;BA.debugLine="Try";
try { //BA.debugLineNum = 525;BA.debugLine="bTmp = su.DecodeBase64(MNChttp.GetString)";
_btmp = _su.DecodeBase64(_mnchttp._vvvvvvvvvvvvvvvvvvvvv2());
 //BA.debugLineNum = 526;BA.debugLine="sTmp = bcTmp.StringFromBytes(bTmp, \"UTF8\")";
_stmp = _bctmp.StringFromBytes(_btmp,"UTF8");
 //BA.debugLineNum = 528;BA.debugLine="parser.Initialize(Fungsi.UnWrap(sTmp))";
_parser.Initialize(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvvvv5(mostCurrent.activityBA,_stmp));
 //BA.debugLineNum = 529;BA.debugLine="MNChttp.Release";
_mnchttp._vvvvvvvvvvvvvvvvvvvvv0();
 } 
       catch (Exception e377) {
			processBA.setLastException(e377); //BA.debugLineNum = 531;BA.debugLine="BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.FailPr";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv7.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvvv5,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 //BA.debugLineNum = 532;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 535;BA.debugLine="Try";
try { //BA.debugLineNum = 536;BA.debugLine="mresult.Initialize";
_mresult.Initialize();
 //BA.debugLineNum = 537;BA.debugLine="mresult = parser.NextObject";
_mresult = _parser.NextObject();
 } 
       catch (Exception e384) {
			processBA.setLastException(e384); //BA.debugLineNum = 539;BA.debugLine="BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.FailPr";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv7.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvvv5,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 //BA.debugLineNum = 540;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 543;BA.debugLine="If mresult.Get(\"Result\") <> \"0\" Then";
if ((_mresult.Get((Object)("Result"))).equals((Object)("0")) == false) { 
 //BA.debugLineNum = 544;BA.debugLine="BDUtama.Msgbox(Fungsi.TitleBox, mresult.Get(\"";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv7.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,BA.ObjectToString(_mresult.Get((Object)("Message"))),mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 546;BA.debugLine="Dim New 			As Map";
_new = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 547;BA.debugLine="Dim Top 			As List";
_top = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 548;BA.debugLine="Dim SubKiri 		As String";
_subkiri = "";
 //BA.debugLineNum = 549;BA.debugLine="Private k1,k2		As Int";
_k1 = 0;
_k2 = 0;
 //BA.debugLineNum = 551;BA.debugLine="New.Initialize";
_new.Initialize();
 //BA.debugLineNum = 552;BA.debugLine="New = mresult.Get(\"Data\")";
_new.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_mresult.Get((Object)("Data"))));
 //BA.debugLineNum = 554;BA.debugLine="Top.Initialize";
_top.Initialize();
 //BA.debugLineNum = 556;BA.debugLine="SubKiri = New.Get(\"subcategory\")";
_subkiri = BA.ObjectToString(_new.Get((Object)("subcategory")));
 //BA.debugLineNum = 557;BA.debugLine="Top = New.Get(\"data\")";
_top.setObject((java.util.List)(_new.Get((Object)("data"))));
 //BA.debugLineNum = 559;BA.debugLine="pTengah.RemoveAllViews";
mostCurrent._ptengah.RemoveAllViews();
 //BA.debugLineNum = 562;BA.debugLine="Dim pgvUtama As PhotoGridView";
_pgvutama = new it.giuseppe.salvi.gridview.library.core.GridViewActivityWrapper();
 //BA.debugLineNum = 564;BA.debugLine="pgvUtama.Initialize(\"pgvEventVOD\")";
_pgvutama.Initialize(mostCurrent.activityBA,"pgvEventVOD");
 //BA.debugLineNum = 565;BA.debugLine="pgvUtama.CacheInMemory = True";
_pgvutama.setCacheInMemory(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 566;BA.debugLine="pgvUtama.CacheOnDisk = True";
_pgvutama.setCacheOnDisk(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 567;BA.debugLine="pgvUtama.CompressQuality = 90";
_pgvutama.setCompressQuality((int) (90));
 //BA.debugLineNum = 568;BA.debugLine="pgvUtama.RoundedBitmap = False";
_pgvutama.setRoundedBitmap(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 569;BA.debugLine="pgvUtama.Gravity=Gravity.FILL";
_pgvutama.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 //BA.debugLineNum = 570;BA.debugLine="pgvUtama.Enabled = True";
_pgvutama.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 571;BA.debugLine="pgvUtama.ProgressBarIndeterminate = False";
_pgvutama.setProgressBarIndeterminate(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 572;BA.debugLine="pgvUtama.ProgressBarVisible = True";
_pgvutama.setProgressBarVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 573;BA.debugLine="pgvUtama.ScaleType = pgvUtama.ScaleType.Fit_X";
_pgvutama.setScaleType(_pgvutama.getScaleType().Fit_XY);
 //BA.debugLineNum = 575;BA.debugLine="If Device = 0 Then";
if (_vvvvvvvvvvvvvvvvvvvvvvvvv5==0) { 
 //BA.debugLineNum = 576;BA.debugLine="If Activity.Width > Activity.Height Then";
if (mostCurrent._activity.getWidth()>mostCurrent._activity.getHeight()) { 
 //BA.debugLineNum = 577;BA.debugLine="pgvUtama.NumColumns = 4";
_pgvutama.setNumColumns((int) (4));
 //BA.debugLineNum = 578;BA.debugLine="pgvUtama.VerticalSpacing = 7%y";
_pgvutama.setVerticalSpacing(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (7),mostCurrent.activityBA));
 }else {
 //BA.debugLineNum = 580;BA.debugLine="pgvUtama.NumColumns = 3";
_pgvutama.setNumColumns((int) (3));
 //BA.debugLineNum = 581;BA.debugLine="pgvUtama.VerticalSpacing = 5%y";
_pgvutama.setVerticalSpacing(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (5),mostCurrent.activityBA));
 };
 //BA.debugLineNum = 584;BA.debugLine="pgvUtama.HorizontalSpacing = 2%x";
_pgvutama.setHorizontalSpacing(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (2),mostCurrent.activityBA));
 //BA.debugLineNum = 585;BA.debugLine="pgvUtama.ItemWidth = 40%x";
_pgvutama.setItemWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (40),mostCurrent.activityBA));
 //BA.debugLineNum = 586;BA.debugLine="pgvUtama.ItemHeight = 120%y";
_pgvutama.setItemHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (120),mostCurrent.activityBA));
 }else {
 //BA.debugLineNum = 588;BA.debugLine="If Activity.Width > Activity.Height Then";
if (mostCurrent._activity.getWidth()>mostCurrent._activity.getHeight()) { 
 //BA.debugLineNum = 589;BA.debugLine="pgvUtama.NumColumns = 4";
_pgvutama.setNumColumns((int) (4));
 //BA.debugLineNum = 590;BA.debugLine="pgvUtama.VerticalSpacing = 7%y";
_pgvutama.setVerticalSpacing(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (7),mostCurrent.activityBA));
 }else {
 //BA.debugLineNum = 592;BA.debugLine="pgvUtama.NumColumns = 3";
_pgvutama.setNumColumns((int) (3));
 //BA.debugLineNum = 593;BA.debugLine="pgvUtama.VerticalSpacing = 5%y";
_pgvutama.setVerticalSpacing(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (5),mostCurrent.activityBA));
 };
 //BA.debugLineNum = 596;BA.debugLine="pgvUtama.HorizontalSpacing = 2%x";
_pgvutama.setHorizontalSpacing(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (2),mostCurrent.activityBA));
 //BA.debugLineNum = 597;BA.debugLine="pgvUtama.ItemWidth = 60%x";
_pgvutama.setItemWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (60),mostCurrent.activityBA));
 //BA.debugLineNum = 598;BA.debugLine="pgvUtama.ItemHeight = 20%y";
_pgvutama.setItemHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (20),mostCurrent.activityBA));
 };
 //BA.debugLineNum = 603;BA.debugLine="Dim Contentvideo 	As Map";
_contentvideo = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 604;BA.debugLine="Dim i 			 			As Int=0";
_i = (int) (0);
 //BA.debugLineNum = 606;BA.debugLine="Contentvideo.Initialize";
_contentvideo.Initialize();
 //BA.debugLineNum = 607;BA.debugLine="Contentvideo.Clear";
_contentvideo.Clear();
 //BA.debugLineNum = 608;BA.debugLine="picture.clear";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvv2.Clear();
 //BA.debugLineNum = 610;BA.debugLine="For Each colKiri As Map In Top";
_colkiri = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group439 = _top;
final int groupLen439 = group439.getSize();
for (int index439 = 0;index439 < groupLen439 ;index439++){
_colkiri.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group439.Get(index439)));
 //BA.debugLineNum = 611;BA.debugLine="For x2=0 To colKiri.Size-1";
{
final int step440 = 1;
final int limit440 = (int) (_colkiri.getSize()-1);
for (_x2 = (int) (0); (step440 > 0 && _x2 <= limit440) || (step440 < 0 && _x2 >= limit440); _x2 = ((int)(0 + _x2 + step440))) {
 //BA.debugLineNum = 612;BA.debugLine="Select colKiri.GetKeyAt(x2)";
switch (BA.switchObjectToInt(_colkiri.GetKeyAt(_x2),(Object)("slug"))) {
case 0:
 //BA.debugLineNum = 614;BA.debugLine="pgvUtama.AddImageFromWeb(Fungsi.mpList.Get";
_pgvutama.AddImageFromWeb(BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("PicVOD")))+BA.ObjectToString(_colkiri.Get((Object)("slug")))+"/poster.jpg");
 break;
}
;
 }
};
 //BA.debugLineNum = 618;BA.debugLine="picture.Put(i, colKiri)";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvv2.Put((Object)(_i),(Object)(_colkiri.getObject()));
 //BA.debugLineNum = 619;BA.debugLine="i = i + 1";
_i = (int) (_i+1);
 }
;
 //BA.debugLineNum = 622;BA.debugLine="pTengah.AddView(pgvUtama, 2%x, 2%y, pTengah.W";
mostCurrent._ptengah.AddView((android.view.View)(_pgvutama.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (2),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (2),mostCurrent.activityBA),(int) (mostCurrent._ptengah.getWidth()-anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (4),mostCurrent.activityBA)),(int) ((mostCurrent._ptengah.getHeight()-mostCurrent._pbmain.getHeight())-anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (4),mostCurrent.activityBA)));
 //BA.debugLineNum = 623;BA.debugLine="GifSmart.Visible = False";
mostCurrent._gifsmart.setVisible(anywheresoftware.b4a.keywords.Common.False);
 };
 break;
}
;
 };
 //BA.debugLineNum = 630;BA.debugLine="End Sub";
return "";
}
public static String  _lblepgdet_click() throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _lbltemp = null;
String[] _strpisah = null;
anywheresoftware.b4a.objects.ConcreteViewWrapper _tmpview = null;
anywheresoftware.b4a.objects.PanelWrapper _tmppanel = null;
anywheresoftware.b4a.objects.ConcreteViewWrapper _z = null;
anywheresoftware.b4a.objects.ImageViewWrapper _tmpimage = null;
String[] _pisah = null;
 //BA.debugLineNum = 806;BA.debugLine="Sub lblEPGDet_Click";
 //BA.debugLineNum = 807;BA.debugLine="Dim lblTemp			As Label = Sender";
_lbltemp = new anywheresoftware.b4a.objects.LabelWrapper();
_lbltemp.setObject((android.widget.TextView)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 808;BA.debugLine="Dim strPisah()	As String";
_strpisah = new String[(int) (0)];
java.util.Arrays.fill(_strpisah,"");
 //BA.debugLineNum = 809;BA.debugLine="Dim tmpView			As View";
_tmpview = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
 //BA.debugLineNum = 811;BA.debugLine="tmpView = Fungsi.GetParent(lblTemp)";
_tmpview = mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvvv4(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(_lbltemp.getObject())));
 //BA.debugLineNum = 813;BA.debugLine="If tmpView Is Panel Then";
if (_tmpview.getObjectOrNull() instanceof android.view.ViewGroup) { 
 //BA.debugLineNum = 814;BA.debugLine="Dim tmpPanel	As Panel = tmpView";
_tmppanel = new anywheresoftware.b4a.objects.PanelWrapper();
_tmppanel.setObject((android.view.ViewGroup)(_tmpview.getObject()));
 //BA.debugLineNum = 816;BA.debugLine="For Each z As View In tmpPanel.GetAllViewsRecurs";
_z = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
final anywheresoftware.b4a.BA.IterableList group602 = _tmppanel.GetAllViewsRecursive();
final int groupLen602 = group602.getSize();
for (int index602 = 0;index602 < groupLen602 ;index602++){
_z.setObject((android.view.View)(group602.Get(index602)));
 //BA.debugLineNum = 817;BA.debugLine="If z Is ImageView Then";
if (_z.getObjectOrNull() instanceof android.widget.ImageView) { 
 //BA.debugLineNum = 818;BA.debugLine="Dim tmpImage 	As ImageView = z";
_tmpimage = new anywheresoftware.b4a.objects.ImageViewWrapper();
_tmpimage.setObject((android.widget.ImageView)(_z.getObject()));
 //BA.debugLineNum = 819;BA.debugLine="Dim Pisah()		As String";
_pisah = new String[(int) (0)];
java.util.Arrays.fill(_pisah,"");
 //BA.debugLineNum = 821;BA.debugLine="Pisah = Regex.Split(\"#\", tmpImage.Tag)";
_pisah = anywheresoftware.b4a.keywords.Common.Regex.Split("#",BA.ObjectToString(_tmpimage.getTag()));
 //BA.debugLineNum = 822;BA.debugLine="If Pisah(1) = \"Thumb\" Then";
if ((_pisah[(int) (1)]).equals("Thumb")) { 
 //BA.debugLineNum = 823;BA.debugLine="EPGList.BmpPre = tmpImage.Bitmap";
mostCurrent._vvvvvvvvvvvvvvvv2._vvvvvvvvvvv2 = (Object)(_tmpimage.getBitmap());
 //BA.debugLineNum = 824;BA.debugLine="EPGList.Aktif = 1";
mostCurrent._vvvvvvvvvvvvvvvv2._vvvvvvvvvvv3 = (int) (1);
 //BA.debugLineNum = 825;BA.debugLine="Exit";
if (true) break;
 };
 };
 }
;
 //BA.debugLineNum = 830;BA.debugLine="strPisah = Regex.Split(\"#\", lblTemp.Tag)";
_strpisah = anywheresoftware.b4a.keywords.Common.Regex.Split("#",BA.ObjectToString(_lbltemp.getTag()));
 //BA.debugLineNum = 831;BA.debugLine="EPGList.ColCode = strPisah(1)";
mostCurrent._vvvvvvvvvvvvvvvv2._vvvvvvvvvv6 = _strpisah[(int) (1)];
 //BA.debugLineNum = 832;BA.debugLine="EPGList.ChaCode =	strPisah(0)";
mostCurrent._vvvvvvvvvvvvvvvv2._vvvvvvvvvv5 = _strpisah[(int) (0)];
 //BA.debugLineNum = 833;BA.debugLine="StartActivity(\"EPGList\")";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)("EPGList"));
 };
 //BA.debugLineNum = 835;BA.debugLine="End Sub";
return "";
}
public static String  _lblgenre_click() throws Exception{
 //BA.debugLineNum = 874;BA.debugLine="Sub lblGenre_Click";
 //BA.debugLineNum = 875;BA.debugLine="Activity_KeyPress(4)";
_activity_keypress((int) (4));
 //BA.debugLineNum = 876;BA.debugLine="End Sub";
return "";
}
public static String  _pgvevent_itemclick(int _position,Object _value) throws Exception{
anywheresoftware.b4a.objects.collections.List _lstchacode = null;
String _strcolcode = "";
String _strcmd = "";
ottplaymedia.mncplaymedia.com.mnchttpjob _chnjob = null;
 //BA.debugLineNum = 686;BA.debugLine="Sub pgvEvent_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 687;BA.debugLine="Dim lstChaCode		As List";
_lstchacode = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 688;BA.debugLine="Dim strColCode		As String";
_strcolcode = "";
 //BA.debugLineNum = 690;BA.debugLine="lstChaCode.Initialize";
_lstchacode.Initialize();
 //BA.debugLineNum = 691;BA.debugLine="lstChaCode.Clear";
_lstchacode.Clear();
 //BA.debugLineNum = 693;BA.debugLine="lstChaCode	=	MapTampung.Get(\"channelcode\")";
_lstchacode.setObject((java.util.List)(mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvv1.Get((Object)("channelcode"))));
 //BA.debugLineNum = 694;BA.debugLine="strColCode	= MapTampung.Get(\"columncode\")";
_strcolcode = BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvv1.Get((Object)("columncode")));
 //BA.debugLineNum = 696;BA.debugLine="If Fungsi.DataConfig.sTab = \"TVOD\" Then";
if ((mostCurrent._vvvvvvvvvvvvvvv0._v0.sTab).equals("TVOD")) { 
 //BA.debugLineNum = 697;BA.debugLine="EPGList.ColCode = strColCode";
mostCurrent._vvvvvvvvvvvvvvvv2._vvvvvvvvvv6 = _strcolcode;
 //BA.debugLineNum = 698;BA.debugLine="EPGList.ChaCode =	lstChaCode.Get(Position)";
mostCurrent._vvvvvvvvvvvvvvvv2._vvvvvvvvvv5 = BA.ObjectToString(_lstchacode.Get(_position));
 //BA.debugLineNum = 699;BA.debugLine="EPGList.BmpPre = Value";
mostCurrent._vvvvvvvvvvvvvvvv2._vvvvvvvvvvv2 = _value;
 //BA.debugLineNum = 700;BA.debugLine="EPGList.Aktif = 2";
mostCurrent._vvvvvvvvvvvvvvvv2._vvvvvvvvvvv3 = (int) (2);
 //BA.debugLineNum = 701;BA.debugLine="StartActivity(\"EPGList\")";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)("EPGList"));
 }else {
 //BA.debugLineNum = 703;BA.debugLine="Dim strCMD 	As String";
_strcmd = "";
 //BA.debugLineNum = 704;BA.debugLine="Dim ChnJob 	As MNCHttpJob";
_chnjob = new ottplaymedia.mncplaymedia.com.mnchttpjob();
 //BA.debugLineNum = 706;BA.debugLine="strCMD = Fungsi.mpList.Get(\"URLLiveTV\") & Fungsi";
_strcmd = BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("URLLiveTV")))+BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("LiveTVchancode")))+BA.ObjectToString(_lstchacode.Get(_position))+"&"+BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("LiveTVcolcode")))+_strcolcode;
 //BA.debugLineNum = 707;BA.debugLine="ChnJob.Initialize(\"GetURLLiveTV\", Me)";
_chnjob._initialize(processBA,"GetURLLiveTV",utama.getObject());
 //BA.debugLineNum = 709;BA.debugLine="Try";
try { //BA.debugLineNum = 710;BA.debugLine="ChnJob.Download(strCMD)";
_chnjob._download(_strcmd);
 //BA.debugLineNum = 711;BA.debugLine="ChnJob.GetRequest.SetHeader(\"Cache-Control\", \"n";
_chnjob._vvvvvvvvvvvvvvvvvvvvv1().SetHeader("Cache-Control","no-cache, must-revalidate");
 //BA.debugLineNum = 712;BA.debugLine="ChnJob.GetRequest.Timeout = DateTime.TicksPerSe";
_chnjob._vvvvvvvvvvvvvvvvvvvvv1().setTimeout((int) (anywheresoftware.b4a.keywords.Common.DateTime.TicksPerSecond*60));
 //BA.debugLineNum = 713;BA.debugLine="ChnJob.Tag = strCMD";
_chnjob._vvvvvvvvvvvvvvvvvvvvvvv1 = (Object)(_strcmd);
 } 
       catch (Exception e521) {
			processBA.setLastException(e521); //BA.debugLineNum = 715;BA.debugLine="ChnJob.Release";
_chnjob._vvvvvvvvvvvvvvvvvvvvv0();
 //BA.debugLineNum = 716;BA.debugLine="Fungsi.mpList.Put(\"Channel\", \"No Channel View\")";
mostCurrent._vvvvvvvvvvvvvvv0._v5.Put((Object)("Channel"),(Object)("No Channel View"));
 //BA.debugLineNum = 717;BA.debugLine="BDUtama.Msgbox(Fungsi.TitleBox, Fungsi.NoGetLis";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv7.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvvv3,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 };
 };
 //BA.debugLineNum = 720;BA.debugLine="End Sub";
return "";
}
public static String  _pgveventvod_itemclick(int _position,Object _value) throws Exception{
Object _a = null;
 //BA.debugLineNum = 878;BA.debugLine="Sub pgvEventVOD_ItemClick (Position As Int, Value";
 //BA.debugLineNum = 879;BA.debugLine="Dim A As Object";
_a = new Object();
 //BA.debugLineNum = 881;BA.debugLine="Content.A = Value";
mostCurrent._vvvvvvvvvvvvvvvv7._vvvvvvvvvvv6 = _value;
 //BA.debugLineNum = 882;BA.debugLine="Content.i = Position";
mostCurrent._vvvvvvvvvvvvvvvv7._vvvvvvvvvvv5 = _position;
 //BA.debugLineNum = 883;BA.debugLine="Content.gambar = picture.Get(Position)";
mostCurrent._vvvvvvvvvvvvvvvv7._vvvvvvvvvvv7.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvv2.Get((Object)(_position))));
 //BA.debugLineNum = 885;BA.debugLine="StartActivity(\"Content\")";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)("Content"));
 //BA.debugLineNum = 886;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvvvvvvvvvvvvvvvvvvvvvv6() throws Exception{
 //BA.debugLineNum = 944;BA.debugLine="Sub WriteMapFile";
 //BA.debugLineNum = 945;BA.debugLine="File.WriteMap(File.DirDefaultExternal, Fungsi.Dat";
anywheresoftware.b4a.keywords.Common.File.WriteMap(anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal(),mostCurrent._vvvvvvvvvvvvvvv0._v0.Username+"_label",mostCurrent._vvvvvvvvvv2);
 //BA.debugLineNum = 946;BA.debugLine="End Sub";
return "";
}
}
