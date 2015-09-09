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

public class content extends Activity implements B4AActivity{
	public static content mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "ottplaymedia.mncplaymedia.com", "ottplaymedia.mncplaymedia.com.content");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (content).");
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
		activityBA = new BA(this, layout, processBA, "ottplaymedia.mncplaymedia.com", "ottplaymedia.mncplaymedia.com.content");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "ottplaymedia.mncplaymedia.com.content", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (content) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (content) Resume **");
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
		return content.class;
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
        BA.LogInfo("** Activity (content) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (content) Resume **");
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
public static int _vvvvvvvvvvv5 = 0;
public static Object _vvvvvvvvvvv6 = null;
public static anywheresoftware.b4a.objects.collections.Map _vvvvvvvvvvv7 = null;
public static anywheresoftware.b4a.phone.PackageManagerWrapper _vvvvvvvvvvvvvvvvvvvvvvvv6 = null;
public static anywheresoftware.b4a.objects.IntentWrapper _vvvvvvvvvvvvvvvvvvvvvvvv4 = null;
public static int _vvvvvvvvvvvvvvvvvvvvvvvv5 = 0;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivmovies = null;
public flm.b4a.betterdialogs.BetterDialogs.CustomDlgParams _vvvvvvvvvvvvvvvvvvvvvvvvvvvvv0 = null;
public flm.b4a.betterdialogs.BetterDialogs _vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv2 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivshare = null;
public static int _vvvvvvvvvvv0 = 0;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivplay = null;
public anywheresoftware.b4a.objects.WebViewWrapper _vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblcerita = null;
public anywheresoftware.b4a.objects.LabelWrapper _vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv4 = null;
public anywheresoftware.b4a.objects.LabelWrapper _vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv5 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbldirec = null;
public anywheresoftware.b4a.objects.LabelWrapper _vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv6 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblgenre = null;
public anywheresoftware.b4a.objects.LabelWrapper _vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv7 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbltitle = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblcast = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblnmcast = null;
public anywheresoftware.b4a.objects.LabelWrapper _vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv0 = null;
public anywheresoftware.b4a.objects.LabelWrapper _vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv1 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblatas = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblbawah = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbltrailer = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivtrailer = null;
public anywheresoftware.b4a.objects.LabelWrapper _vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv3 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv4 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv5 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblcomments = null;
public anywheresoftware.b4a.objects.ButtonWrapper _vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv6 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv7 = null;
public anywheresoftware.b4a.objects.PanelWrapper _putama = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv0 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblnmwriter = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivcomments = null;
public static int _vvvvvvvvvvvv1 = 0;
public com.datasteam.b4a.xtraviews.MovieViewControl _gifsmart = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _vvvvvvvvvvvvvvv5 = null;
public b4a.example.dateutils _vvvvvvvvvvvvvvv6 = null;
public ottplaymedia.mncplaymedia.com.main _vvvvvvvvvvvvvvv7 = null;
public ottplaymedia.mncplaymedia.com.fungsi _vvvvvvvvvvvvvvv0 = null;
public ottplaymedia.mncplaymedia.com.utama _vvvvvvvvvvvvvvvv1 = null;
public ottplaymedia.mncplaymedia.com.epglist _vvvvvvvvvvvvvvvv2 = null;
public ottplaymedia.mncplaymedia.com.register _vvvvvvvvvvvvvvvv3 = null;
public ottplaymedia.mncplaymedia.com.pilihan _vvvvvvvvvvvvvvvv4 = null;
public ottplaymedia.mncplaymedia.com.epgdownloader _vvvvvvvvvvvvvvvv5 = null;
public ottplaymedia.mncplaymedia.com.imagedownloader _vvvvvvvvvvvvvvvv6 = null;
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
anywheresoftware.b4a.objects.collections.Map _cate0 = null;
anywheresoftware.b4a.objects.collections.List _cate1 = null;
anywheresoftware.b4a.objects.collections.Map _cate2 = null;
anywheresoftware.b4a.objects.collections.Map _genre0 = null;
anywheresoftware.b4a.objects.collections.List _genre1 = null;
anywheresoftware.b4a.objects.collections.Map _gender2 = null;
anywheresoftware.b4a.objects.collections.Map _cast0 = null;
anywheresoftware.b4a.objects.collections.List _cast1 = null;
anywheresoftware.b4a.objects.collections.Map _cast2 = null;
anywheresoftware.b4a.objects.collections.Map _writer0 = null;
anywheresoftware.b4a.objects.collections.List _writer1 = null;
anywheresoftware.b4a.objects.collections.Map _writer2 = null;
int _x2 = 0;
 //BA.debugLineNum = 69;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 74;BA.debugLine="Activity.LoadLayout(\"Content\")";
mostCurrent._activity.LoadLayout("Content",mostCurrent.activityBA);
 //BA.debugLineNum = 76;BA.debugLine="ivMovies.Bitmap = a";
mostCurrent._ivmovies.setBitmap((android.graphics.Bitmap)(_vvvvvvvvvvv6));
 //BA.debugLineNum = 77;BA.debugLine="GifSmart.Load(File.DirAssets, \"spin.gif\", True)";
mostCurrent._gifsmart.Load(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"spin.gif",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 81;BA.debugLine="Dim Cate0 As Map";
_cate0 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 82;BA.debugLine="Cate0.Initialize";
_cate0.Initialize();
 //BA.debugLineNum = 83;BA.debugLine="gambar.Get(\"contentDirectors\")";
_vvvvvvvvvvv7.Get((Object)("contentDirectors"));
 //BA.debugLineNum = 85;BA.debugLine="Dim Cate1 As List = gambar.Get(\"contentDirectors";
_cate1 = new anywheresoftware.b4a.objects.collections.List();
_cate1.setObject((java.util.List)(_vvvvvvvvvvv7.Get((Object)("contentDirectors"))));
 //BA.debugLineNum = 86;BA.debugLine="For Each Cate2 As Map In Cate1";
_cate2 = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group58 = _cate1;
final int groupLen58 = group58.getSize();
for (int index58 = 0;index58 < groupLen58 ;index58++){
_cate2.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group58.Get(index58)));
 //BA.debugLineNum = 87;BA.debugLine="Cate0 = Cate2.Get(\"idDirector\")";
_cate0.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_cate2.Get((Object)("idDirector"))));
 }
;
 //BA.debugLineNum = 89;BA.debugLine="Cate0.Get(\"name\")";
_cate0.Get((Object)("name"));
 //BA.debugLineNum = 90;BA.debugLine="lblDirec.Text = Cate0.Get(\"name\")";
mostCurrent._lbldirec.setText(_cate0.Get((Object)("name")));
 //BA.debugLineNum = 94;BA.debugLine="Dim Genre0 As Map";
_genre0 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 95;BA.debugLine="Genre0.Initialize";
_genre0.Initialize();
 //BA.debugLineNum = 96;BA.debugLine="gambar.Get(\"contentGenres\")";
_vvvvvvvvvvv7.Get((Object)("contentGenres"));
 //BA.debugLineNum = 97;BA.debugLine="Dim Genre1 As List = gambar.Get(\"contentGenres\")";
_genre1 = new anywheresoftware.b4a.objects.collections.List();
_genre1.setObject((java.util.List)(_vvvvvvvvvvv7.Get((Object)("contentGenres"))));
 //BA.debugLineNum = 98;BA.debugLine="For Each Gender2 As Map In Genre1";
_gender2 = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group67 = _genre1;
final int groupLen67 = group67.getSize();
for (int index67 = 0;index67 < groupLen67 ;index67++){
_gender2.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group67.Get(index67)));
 //BA.debugLineNum = 99;BA.debugLine="Genre0 = Gender2.Get(\"idGenre\")";
_genre0.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_gender2.Get((Object)("idGenre"))));
 }
;
 //BA.debugLineNum = 101;BA.debugLine="Genre0.Get(\"name\")";
_genre0.Get((Object)("name"));
 //BA.debugLineNum = 102;BA.debugLine="lblGenre.Text = Genre0.Get(\"name\")";
mostCurrent._lblgenre.setText(_genre0.Get((Object)("name")));
 //BA.debugLineNum = 105;BA.debugLine="Dim Cast0 As Map";
_cast0 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 106;BA.debugLine="Cast0.Initialize";
_cast0.Initialize();
 //BA.debugLineNum = 107;BA.debugLine="gambar.Get(\"contentCasts\")";
_vvvvvvvvvvv7.Get((Object)("contentCasts"));
 //BA.debugLineNum = 108;BA.debugLine="Dim Cast1 As List = gambar.Get(\"contentCasts\")";
_cast1 = new anywheresoftware.b4a.objects.collections.List();
_cast1.setObject((java.util.List)(_vvvvvvvvvvv7.Get((Object)("contentCasts"))));
 //BA.debugLineNum = 109;BA.debugLine="For Each Cast2 As Map In Cast1";
_cast2 = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group76 = _cast1;
final int groupLen76 = group76.getSize();
for (int index76 = 0;index76 < groupLen76 ;index76++){
_cast2.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group76.Get(index76)));
 //BA.debugLineNum = 110;BA.debugLine="Cast0 = Cast2.Get(\"idCast\")";
_cast0.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_cast2.Get((Object)("idCast"))));
 }
;
 //BA.debugLineNum = 113;BA.debugLine="Cast0.Get(\"name\")";
_cast0.Get((Object)("name"));
 //BA.debugLineNum = 115;BA.debugLine="lblNmCast.Text = Cast0.Get(\"name\")";
mostCurrent._lblnmcast.setText(_cast0.Get((Object)("name")));
 //BA.debugLineNum = 118;BA.debugLine="Dim Writer0 As Map";
_writer0 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 119;BA.debugLine="Writer0.Initialize";
_writer0.Initialize();
 //BA.debugLineNum = 120;BA.debugLine="gambar.Get(\"contentProducers\")";
_vvvvvvvvvvv7.Get((Object)("contentProducers"));
 //BA.debugLineNum = 122;BA.debugLine="Dim Writer1 As List = gambar.Get(\"contentProduce";
_writer1 = new anywheresoftware.b4a.objects.collections.List();
_writer1.setObject((java.util.List)(_vvvvvvvvvvv7.Get((Object)("contentProducers"))));
 //BA.debugLineNum = 123;BA.debugLine="For Each Writer2 As Map In Writer1";
_writer2 = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group85 = _writer1;
final int groupLen85 = group85.getSize();
for (int index85 = 0;index85 < groupLen85 ;index85++){
_writer2.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group85.Get(index85)));
 //BA.debugLineNum = 124;BA.debugLine="Writer0 = Writer2.Get(\"idProducer\")";
_writer0.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_writer2.Get((Object)("idProducer"))));
 }
;
 //BA.debugLineNum = 127;BA.debugLine="Writer0.Get(\"name\")";
_writer0.Get((Object)("name"));
 //BA.debugLineNum = 129;BA.debugLine="lblNmWriter.Text = Writer0.Get(\"name\")";
mostCurrent._lblnmwriter.setText(_writer0.Get((Object)("name")));
 //BA.debugLineNum = 131;BA.debugLine="Log(gambar)";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(_vvvvvvvvvvv7));
 //BA.debugLineNum = 132;BA.debugLine="For x2=0 To gambar.Size-1";
{
final int step91 = 1;
final int limit91 = (int) (_vvvvvvvvvvv7.getSize()-1);
for (_x2 = (int) (0); (step91 > 0 && _x2 <= limit91) || (step91 < 0 && _x2 >= limit91); _x2 = ((int)(0 + _x2 + step91))) {
 //BA.debugLineNum = 133;BA.debugLine="Select gambar.GetKeyAt(x2)";
switch (BA.switchObjectToInt(_vvvvvvvvvvv7.GetKeyAt(_x2),(Object)("synopsis"),(Object)("type"),(Object)("title"))) {
case 0:
 //BA.debugLineNum = 135;BA.debugLine="lblCerita.Text = gambar.Get(\"synopsis\")";
mostCurrent._lblcerita.setText(_vvvvvvvvvvv7.Get((Object)("synopsis")));
 break;
case 1:
 //BA.debugLineNum = 137;BA.debugLine="lblBawah.Text = gambar.Get(\"type\")";
mostCurrent._lblbawah.setText(_vvvvvvvvvvv7.Get((Object)("type")));
 break;
case 2:
 //BA.debugLineNum = 139;BA.debugLine="lblAtas.Text = gambar.Get(\"title\")";
mostCurrent._lblatas.setText(_vvvvvvvvvvv7.Get((Object)("title")));
 //BA.debugLineNum = 140;BA.debugLine="lblTitle.Text = gambar.Get(\"title\")";
mostCurrent._lbltitle.setText(_vvvvvvvvvvv7.Get((Object)("title")));
 break;
}
;
 }
};
 //BA.debugLineNum = 146;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 272;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 273;BA.debugLine="If KeyCode = 4 Then ' Back Key";
if (_keycode==4) { 
 //BA.debugLineNum = 274;BA.debugLine="CallSub(ImageDownloader, \"ActivityIsPaused\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()),"ActivityIsPaused");
 //BA.debugLineNum = 275;BA.debugLine="CallSub(EPGDownloader, \"ActivityIsPaused\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()),"ActivityIsPaused");
 //BA.debugLineNum = 277;BA.debugLine="StopService(MNCUtils2Service)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvvv1.getObject()));
 //BA.debugLineNum = 278;BA.debugLine="CancelScheduledService(MNCUtils2Service)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvvv1.getObject()));
 //BA.debugLineNum = 279;BA.debugLine="StopService(ImageDownloader)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()));
 //BA.debugLineNum = 280;BA.debugLine="CancelScheduledService(ImageDownloader)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()));
 //BA.debugLineNum = 281;BA.debugLine="StopService(EPGDownloader)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()));
 //BA.debugLineNum = 282;BA.debugLine="CancelScheduledService(EPGDownloader)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()));
 //BA.debugLineNum = 284;BA.debugLine="StartActivity(Utama)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv1.getObject()));
 //BA.debugLineNum = 285;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 }else {
 //BA.debugLineNum = 287;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 289;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 153;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 154;BA.debugLine="ivPlay.Visible = False";
mostCurrent._ivplay.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 155;BA.debugLine="GifSmart.Visible = True";
mostCurrent._gifsmart.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 156;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 148;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 149;BA.debugLine="ivPlay.Visible = True";
mostCurrent._ivplay.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 150;BA.debugLine="GifSmart.Visible = False";
mostCurrent._gifsmart.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 151;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 20;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 24;BA.debugLine="Dim ivMovies 		As ImageView";
mostCurrent._ivmovies = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private Params		As BD_CustomDlgParams";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvvv0 = new flm.b4a.betterdialogs.BetterDialogs.CustomDlgParams();
 //BA.debugLineNum = 26;BA.debugLine="Private BD			As BetterDialogs";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv1 = new flm.b4a.betterdialogs.BetterDialogs();
 //BA.debugLineNum = 27;BA.debugLine="Private pShare		As Panel";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv2 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private ivShare 	As ImageView";
mostCurrent._ivshare = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Dim iShare 			As Int=1";
_vvvvvvvvvvv0 = (int) (1);
 //BA.debugLineNum = 30;BA.debugLine="Private ivPlay 		As ImageView";
mostCurrent._ivplay = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private wvOrder 	As WebView";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv3 = new anywheresoftware.b4a.objects.WebViewWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private lblCerita   As Label";
mostCurrent._lblcerita = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private lblFilm 	As Label";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv4 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private lblCate 	As Label";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv5 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private lblDirec 	As Label";
mostCurrent._lbldirec = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private lblNama 	As Label";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv6 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private lblGenre 	As Label";
mostCurrent._lblgenre = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private lblJudul 	As Label";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv7 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private lblTitle 	As Label";
mostCurrent._lbltitle = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private lblCast As Label";
mostCurrent._lblcast = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private lblNmCast As Label";
mostCurrent._lblnmcast = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Private lblTake As Label";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv0 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Private lblAllSyn As Label";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Private ivMovie As ImageView";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv2 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Private lblAtas As Label";
mostCurrent._lblatas = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 47;BA.debugLine="Private lblBawah As Label";
mostCurrent._lblbawah = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 48;BA.debugLine="Private lblTrailer As Label";
mostCurrent._lbltrailer = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 49;BA.debugLine="Private ivTrailer As ImageView";
mostCurrent._ivtrailer = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 50;BA.debugLine="Private lblComment As Label";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 51;BA.debugLine="Private ivComment As ImageView";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv4 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 52;BA.debugLine="Private etDesc As EditText";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv5 = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 53;BA.debugLine="Private lblComments As Label";
mostCurrent._lblcomments = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 54;BA.debugLine="Private bShare As Button";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv6 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 55;BA.debugLine="Private bCancel As Button";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv7 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 56;BA.debugLine="Private pUtama As Panel";
mostCurrent._putama = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 57;BA.debugLine="Private ivCancel As ImageView";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv0 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 58;BA.debugLine="Private ivAction As ImageView";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 59;BA.debugLine="Private lblAction As Label";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 60;BA.debugLine="Private lblNmWrite As Label";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 61;BA.debugLine="Private lblNmWriter As Label";
mostCurrent._lblnmwriter = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 62;BA.debugLine="Private ivComments As ImageView";
mostCurrent._ivcomments = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 65;BA.debugLine="Dim git 		 As Int";
_vvvvvvvvvvvv1 = 0;
 //BA.debugLineNum = 66;BA.debugLine="Private GifSmart As MovieViewControl";
mostCurrent._gifsmart = new com.datasteam.b4a.xtraviews.MovieViewControl();
 //BA.debugLineNum = 67;BA.debugLine="End Sub";
return "";
}
public static String  _ivback_click() throws Exception{
 //BA.debugLineNum = 158;BA.debugLine="Sub ivBack_Click";
 //BA.debugLineNum = 159;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 160;BA.debugLine="End Sub";
return "";
}
public static String  _ivcancel_click() throws Exception{
 //BA.debugLineNum = 249;BA.debugLine="Sub ivCancel_Click";
 //BA.debugLineNum = 250;BA.debugLine="StartActivity(\"Content\")";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)("Content"));
 //BA.debugLineNum = 251;BA.debugLine="End Sub";
return "";
}
public static String  _ivcomments_click() throws Exception{
 //BA.debugLineNum = 268;BA.debugLine="Sub ivComments_Click";
 //BA.debugLineNum = 269;BA.debugLine="lblComments_Click";
_lblcomments_click();
 //BA.debugLineNum = 270;BA.debugLine="End Sub";
return "";
}
public static String  _ivmovies_click() throws Exception{
 //BA.debugLineNum = 222;BA.debugLine="Sub ivMovies_Click";
 //BA.debugLineNum = 224;BA.debugLine="End Sub";
return "";
}
public static String  _ivplay_click() throws Exception{
int _x2 = 0;
anywheresoftware.b4a.objects.collections.List _paclist = null;
 //BA.debugLineNum = 166;BA.debugLine="Sub ivPlay_Click";
 //BA.debugLineNum = 168;BA.debugLine="ivPlay.Visible = False";
mostCurrent._ivplay.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 169;BA.debugLine="GifSmart.Visible=True";
mostCurrent._gifsmart.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 171;BA.debugLine="CallSub(ImageDownloader, \"ActivityIsPaused\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()),"ActivityIsPaused");
 //BA.debugLineNum = 172;BA.debugLine="CallSub(EPGDownloader, \"ActivityIsPaused\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()),"ActivityIsPaused");
 //BA.debugLineNum = 174;BA.debugLine="StopService(MNCUtils2Service)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvvv1.getObject()));
 //BA.debugLineNum = 175;BA.debugLine="CancelScheduledService(MNCUtils2Service)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvvv1.getObject()));
 //BA.debugLineNum = 176;BA.debugLine="StopService(ImageDownloader)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()));
 //BA.debugLineNum = 177;BA.debugLine="CancelScheduledService(ImageDownloader)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()));
 //BA.debugLineNum = 178;BA.debugLine="StopService(EPGDownloader)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()));
 //BA.debugLineNum = 179;BA.debugLine="CancelScheduledService(EPGDownloader)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()));
 //BA.debugLineNum = 182;BA.debugLine="For x2=0 To gambar.Size-1";
{
final int step128 = 1;
final int limit128 = (int) (_vvvvvvvvvvv7.getSize()-1);
for (_x2 = (int) (0); (step128 > 0 && _x2 <= limit128) || (step128 < 0 && _x2 >= limit128); _x2 = ((int)(0 + _x2 + step128))) {
 //BA.debugLineNum = 184;BA.debugLine="Select gambar.GetKeyAt(x2)";
switch (BA.switchObjectToInt(_vvvvvvvvvvv7.GetKeyAt(_x2),(Object)("pathVideo"))) {
case 0:
 //BA.debugLineNum = 186;BA.debugLine="If gambar.Get(\"pathVideo\") = Null Then";
if (_vvvvvvvvvvv7.Get((Object)("pathVideo"))== null) { 
 //BA.debugLineNum = 187;BA.debugLine="Msgbox2(\"Video not found !\", \"Moviebay\", \"OK\",";
anywheresoftware.b4a.keywords.Common.Msgbox2("Video not found !","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 190;BA.debugLine="If File.Exists(Fungsi.DirDBS, \"nexplayersdklit";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._vvvvvvvvvvvvvvv0._v6,"nexplayersdklitevm.apk")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 191;BA.debugLine="ProgressDialogShow2(\"Please wait...\", False)";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow2(mostCurrent.activityBA,"Please wait...",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 192;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 //BA.debugLineNum = 193;BA.debugLine="File.Copy(File.DirAssets, \"nexplayersdklitevm";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"nexplayersdklitevm.apk",mostCurrent._vvvvvvvvvvvvvvv0._v6,"nexplayersdklitevm.apk");
 //BA.debugLineNum = 194;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 195;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 };
 //BA.debugLineNum = 198;BA.debugLine="Dim pacList As List = pm.GetInstalledPackages";
_paclist = new anywheresoftware.b4a.objects.collections.List();
_paclist = _vvvvvvvvvvvvvvvvvvvvvvvv6.GetInstalledPackages();
 //BA.debugLineNum = 199;BA.debugLine="ipack = pacList.IndexOf(\"com.nexstreaming.app.";
_vvvvvvvvvvvvvvvvvvvvvvvv5 = _paclist.IndexOf((Object)("com.nexstreaming.app.nexplayersample"));
 //BA.debugLineNum = 201;BA.debugLine="If ipack < 0 Then";
if (_vvvvvvvvvvvvvvvvvvvvvvvv5<0) { 
 //BA.debugLineNum = 202;BA.debugLine="ups.Initialize(ups.ACTION_VIEW, \"file://\" & F";
_vvvvvvvvvvvvvvvvvvvvvvvv4.Initialize(_vvvvvvvvvvvvvvvvvvvvvvvv4.ACTION_VIEW,"file://"+anywheresoftware.b4a.keywords.Common.File.Combine(mostCurrent._vvvvvvvvvvvvvvv0._v6,"nexplayersdklitevm.apk"));
 //BA.debugLineNum = 203;BA.debugLine="ups.SetType(\"application/vnd.android.package-";
_vvvvvvvvvvvvvvvvvvvvvvvv4.SetType("application/vnd.android.package-archive");
 //BA.debugLineNum = 204;BA.debugLine="StartActivity(ups)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_vvvvvvvvvvvvvvvvvvvvvvvv4.getObject()));
 };
 //BA.debugLineNum = 208;BA.debugLine="ups.Initialize(ups.ACTION_MAIN, \"\")";
_vvvvvvvvvvvvvvvvvvvvvvvv4.Initialize(_vvvvvvvvvvvvvvvvvvvvvvvv4.ACTION_MAIN,"");
 //BA.debugLineNum = 209;BA.debugLine="ups = pm.GetApplicationIntent(\"com.nexstreami";
_vvvvvvvvvvvvvvvvvvvvvvvv4 = _vvvvvvvvvvvvvvvvvvvvvvvv6.GetApplicationIntent("com.nexstreaming.app.nexplayersample");
 //BA.debugLineNum = 211;BA.debugLine="ups.PutExtra(\"LinkURL\", gambar.Get(\"pathVideo\"";
_vvvvvvvvvvvvvvvvvvvvvvvv4.PutExtra("LinkURL",_vvvvvvvvvvv7.Get((Object)("pathVideo")));
 //BA.debugLineNum = 212;BA.debugLine="ups.PutExtra(\"Company\", \"PT-Infokom\")";
_vvvvvvvvvvvvvvvvvvvvvvvv4.PutExtra("Company",(Object)("PT-Infokom"));
 //BA.debugLineNum = 213;BA.debugLine="ups.PutExtra(\"VCASAddr\", \"202.80.222.36:80\")";
_vvvvvvvvvvvvvvvvvvvvvvvv4.PutExtra("VCASAddr",(Object)("202.80.222.36:80"));
 //BA.debugLineNum = 215;BA.debugLine="If ups.IsInitialized Then StartActivity(ups)";
if (_vvvvvvvvvvvvvvvvvvvvvvvv4.IsInitialized()) { 
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_vvvvvvvvvvvvvvvvvvvvvvvv4.getObject()));};
 };
 break;
}
;
 }
};
 //BA.debugLineNum = 220;BA.debugLine="End Sub";
return "";
}
public static String  _ivshare_click() throws Exception{
 //BA.debugLineNum = 162;BA.debugLine="Sub ivShare_Click";
 //BA.debugLineNum = 163;BA.debugLine="StartActivity(\"Share\")";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)("Share"));
 //BA.debugLineNum = 164;BA.debugLine="End Sub";
return "";
}
public static String  _ivtrailer_click() throws Exception{
 //BA.debugLineNum = 246;BA.debugLine="Sub ivTrailer_Click";
 //BA.debugLineNum = 247;BA.debugLine="lblTrailer_Click";
_lbltrailer_click();
 //BA.debugLineNum = 248;BA.debugLine="End Sub";
return "";
}
public static String  _lblcomments_click() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _pnlbody = null;
 //BA.debugLineNum = 252;BA.debugLine="Sub lblComments_Click";
 //BA.debugLineNum = 253;BA.debugLine="Private pnlBody As Panel";
_pnlbody = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 254;BA.debugLine="pnlBody.Initialize(\"\")";
_pnlbody.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 255;BA.debugLine="pnlBody.Color = Colors.Transparent";
_pnlbody.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 256;BA.debugLine="pnlBody.LoadLayout(\"comments\")";
_pnlbody.LoadLayout("comments",mostCurrent.activityBA);
 //BA.debugLineNum = 258;BA.debugLine="etDesc.Background = Fungsi.GetDrawable (\"pass_inp";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv5.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"pass_input_")));
 //BA.debugLineNum = 260;BA.debugLine="Params.Initialize";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvvv0.Initialize();
 //BA.debugLineNum = 261;BA.debugLine="Params.DialogBody = pnlBody";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvvv0.setDialogBody((Object)(_pnlbody.getObject()));
 //BA.debugLineNum = 262;BA.debugLine="Params.BodyWidth = pUtama.Width";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvvv0.setBodyWidth(mostCurrent._putama.getWidth());
 //BA.debugLineNum = 263;BA.debugLine="Params.BodyHeight = pUtama.Height";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvvv0.setBodyHeight(mostCurrent._putama.getHeight());
 //BA.debugLineNum = 265;BA.debugLine="BD.CustomDialog(Params, \"CD2\")";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv1.CustomDialog(mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvvv0,"CD2",mostCurrent.activityBA);
 //BA.debugLineNum = 266;BA.debugLine="End Sub";
return "";
}
public static String  _lbltrailer_click() throws Exception{
int _x2 = 0;
 //BA.debugLineNum = 226;BA.debugLine="Sub lblTrailer_Click";
 //BA.debugLineNum = 227;BA.debugLine="For x2=0 To gambar.Size-1";
{
final int step161 = 1;
final int limit161 = (int) (_vvvvvvvvvvv7.getSize()-1);
for (_x2 = (int) (0); (step161 > 0 && _x2 <= limit161) || (step161 < 0 && _x2 >= limit161); _x2 = ((int)(0 + _x2 + step161))) {
 //BA.debugLineNum = 228;BA.debugLine="Select gambar.GetKeyAt(x2)";
switch (BA.switchObjectToInt(_vvvvvvvvvvv7.GetKeyAt(_x2),(Object)("pathVideo"))) {
case 0:
 //BA.debugLineNum = 230;BA.debugLine="If gambar.Get(\"pathVideo\") = Null Then";
if (_vvvvvvvvvvv7.Get((Object)("pathVideo"))== null) { 
 //BA.debugLineNum = 231;BA.debugLine="Msgbox2(\"Video not found !\", \"Moviebay\", \"OK\",";
anywheresoftware.b4a.keywords.Common.Msgbox2("Video not found !","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 234;BA.debugLine="ups.Initialize(ups.ACTION_MAIN, \"\")";
_vvvvvvvvvvvvvvvvvvvvvvvv4.Initialize(_vvvvvvvvvvvvvvvvvvvvvvvv4.ACTION_MAIN,"");
 //BA.debugLineNum = 235;BA.debugLine="ups = pm.GetApplicationIntent(\"com.nexstreami";
_vvvvvvvvvvvvvvvvvvvvvvvv4 = _vvvvvvvvvvvvvvvvvvvvvvvv6.GetApplicationIntent("com.nexstreaming.app.nexplayersample");
 //BA.debugLineNum = 236;BA.debugLine="ups.PutExtra(\"LinkURL\", gambar.Get(\"http://med";
_vvvvvvvvvvvvvvvvvvvvvvvv4.PutExtra("LinkURL",_vvvvvvvvvvv7.Get((Object)("http://media.mncplaymedia.net/hls2/animalplanet/animalplanet.m3u8")));
 //BA.debugLineNum = 237;BA.debugLine="ups.PutExtra(\"Company\", \"PT-Infokom\")";
_vvvvvvvvvvvvvvvvvvvvvvvv4.PutExtra("Company",(Object)("PT-Infokom"));
 //BA.debugLineNum = 238;BA.debugLine="ups.PutExtra(\"VCASAddr\", \"202.80.222.36:80\")";
_vvvvvvvvvvvvvvvvvvvvvvvv4.PutExtra("VCASAddr",(Object)("202.80.222.36:80"));
 //BA.debugLineNum = 239;BA.debugLine="If ups.IsInitialized Then StartActivity(ups)";
if (_vvvvvvvvvvvvvvvvvvvvvvvv4.IsInitialized()) { 
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_vvvvvvvvvvvvvvvvvvvvvvvv4.getObject()));};
 //BA.debugLineNum = 240;BA.debugLine="StartActivity(ups)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_vvvvvvvvvvvvvvvvvvvvvvvv4.getObject()));
 };
 break;
}
;
 }
};
 //BA.debugLineNum = 245;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="Dim i			As Int";
_vvvvvvvvvvv5 = 0;
 //BA.debugLineNum = 11;BA.debugLine="Dim a			As Object";
_vvvvvvvvvvv6 = new Object();
 //BA.debugLineNum = 12;BA.debugLine="Dim gambar 		As Map";
_vvvvvvvvvvv7 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 13;BA.debugLine="Private pm 		As PackageManager";
_vvvvvvvvvvvvvvvvvvvvvvvv6 = new anywheresoftware.b4a.phone.PackageManagerWrapper();
 //BA.debugLineNum = 14;BA.debugLine="Private ups 	As Intent";
_vvvvvvvvvvvvvvvvvvvvvvvv4 = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 15;BA.debugLine="Private ipack	As Int";
_vvvvvvvvvvvvvvvvvvvvvvvv5 = 0;
 //BA.debugLineNum = 18;BA.debugLine="End Sub";
return "";
}
}
