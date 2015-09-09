package ottplaymedia.mncplaymedia.com.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_login{

public static void LS_320x480_1(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("plogin").vw.setLeft((int)(0d));
views.get("plogin").vw.setWidth((int)((80d / 100 * width) - (0d)));
views.get("plogin").vw.setHeight((int)((80d / 100 * height)));
views.get("plogin").vw.setLeft((int)((50d / 100 * width) - (views.get("plogin").vw.getWidth() / 2)));
views.get("iv1").vw.setTop((int)(0d));
views.get("iv1").vw.setLeft((int)(0d));
views.get("iv2").vw.setTop((int)(0d));
views.get("iv2").vw.setLeft((int)(0d));
views.get("etemail").vw.setLeft((int)((views.get("iv1").vw.getLeft() + views.get("iv1").vw.getWidth())-(1.5d / 100 * width)));
views.get("etemail").vw.setWidth((int)((views.get("plogin").vw.getWidth()) - ((views.get("iv1").vw.getLeft() + views.get("iv1").vw.getWidth())-(1.5d / 100 * width))));
views.get("etemail").vw.setTop((int)(0d));
views.get("etemail").vw.setHeight((int)((views.get("iv1").vw.getHeight())));
views.get("iv3").vw.setTop((int)((views.get("iv1").vw.getTop() + views.get("iv1").vw.getHeight())+(1.5d / 100 * height)));
views.get("iv3").vw.setLeft((int)(0d));
views.get("iv4").vw.setTop((int)((views.get("iv1").vw.getTop() + views.get("iv1").vw.getHeight())+(1.5d / 100 * height)));
views.get("iv4").vw.setLeft((int)(0d));
views.get("etpass").vw.setLeft((int)((views.get("iv3").vw.getLeft() + views.get("iv3").vw.getWidth())-(1.5d / 100 * width)));
views.get("etpass").vw.setWidth((int)((views.get("plogin").vw.getWidth()) - ((views.get("iv3").vw.getLeft() + views.get("iv3").vw.getWidth())-(1.5d / 100 * width))));
views.get("etpass").vw.setTop((int)((views.get("etemail").vw.getTop() + views.get("etemail").vw.getHeight())+(1.5d / 100 * height)));
views.get("etpass").vw.setHeight((int)((views.get("iv3").vw.getHeight())));
views.get("bsubmit").vw.setTop((int)((views.get("etpass").vw.getTop() + views.get("etpass").vw.getHeight())+(2d / 100 * height)));
views.get("bsubmit").vw.setHeight((int)((views.get("etpass").vw.getHeight())));
views.get("bsubmit").vw.setLeft((int)(0d));
views.get("bsubmit").vw.setWidth((int)((views.get("plogin").vw.getWidth()) - (0d)));
views.get("lbllg").vw.setLeft((int)((views.get("bsubmit").vw.getLeft())));
views.get("lbllg").vw.setWidth((int)((views.get("bsubmit").vw.getWidth())));
views.get("lbllg").vw.setTop((int)((views.get("bsubmit").vw.getTop() + views.get("bsubmit").vw.getHeight())+(2d / 100 * height)));
views.get("plogin").vw.setHeight((int)((views.get("lbllg").vw.getTop() + views.get("lbllg").vw.getHeight())+(15d / 100 * height)));
views.get("plogin").vw.setTop((int)((50d / 100 * height) - (views.get("plogin").vw.getHeight() / 2)));
views.get("breg").vw.setTop((int)((97d / 100 * height) - (views.get("breg").vw.getHeight())));
views.get("breg").vw.setLeft((int)((50d / 100 * width) - (views.get("breg").vw.getWidth() / 2)));
views.get("breg").vw.setLeft((int)((views.get("plogin").vw.getLeft())));
views.get("breg").vw.setWidth((int)((views.get("plogin").vw.getWidth())));
views.get("breg").vw.setHeight((int)((views.get("bsubmit").vw.getHeight())));

}
public static void LS_480x320_1(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("plogin").vw.setLeft((int)(0d));
views.get("plogin").vw.setWidth((int)((50d / 100 * width) - (0d)));
views.get("plogin").vw.setHeight((int)((80d / 100 * height)));
views.get("plogin").vw.setLeft((int)((50d / 100 * width) - (views.get("plogin").vw.getWidth() / 2)));
views.get("iv1").vw.setTop((int)(0d));
views.get("iv1").vw.setLeft((int)(0d));
views.get("iv2").vw.setTop((int)(0d));
views.get("iv2").vw.setLeft((int)(0d));
views.get("etemail").vw.setLeft((int)((views.get("iv1").vw.getLeft() + views.get("iv1").vw.getWidth())-(1.5d / 100 * width)));
views.get("etemail").vw.setWidth((int)((views.get("plogin").vw.getWidth()) - ((views.get("iv1").vw.getLeft() + views.get("iv1").vw.getWidth())-(1.5d / 100 * width))));
views.get("etemail").vw.setTop((int)(0d));
views.get("etemail").vw.setHeight((int)((views.get("iv1").vw.getHeight())));
views.get("iv3").vw.setTop((int)((views.get("iv1").vw.getTop() + views.get("iv1").vw.getHeight())+(1.5d / 100 * height)));
views.get("iv3").vw.setLeft((int)(0d));
views.get("iv4").vw.setTop((int)((views.get("iv1").vw.getTop() + views.get("iv1").vw.getHeight())+(1.5d / 100 * height)));
views.get("iv4").vw.setLeft((int)(0d));
views.get("etpass").vw.setLeft((int)((views.get("iv3").vw.getLeft() + views.get("iv3").vw.getWidth())-(1.5d / 100 * width)));
views.get("etpass").vw.setWidth((int)((views.get("plogin").vw.getWidth()) - ((views.get("iv3").vw.getLeft() + views.get("iv3").vw.getWidth())-(1.5d / 100 * width))));
views.get("etpass").vw.setTop((int)((views.get("etemail").vw.getTop() + views.get("etemail").vw.getHeight())+(1.5d / 100 * height)));
views.get("etpass").vw.setHeight((int)((views.get("iv3").vw.getHeight())));
views.get("bsubmit").vw.setTop((int)((views.get("etpass").vw.getTop() + views.get("etpass").vw.getHeight())+(2d / 100 * height)));
views.get("bsubmit").vw.setHeight((int)((views.get("etpass").vw.getHeight())));
views.get("bsubmit").vw.setLeft((int)(0d));
views.get("bsubmit").vw.setWidth((int)((views.get("plogin").vw.getWidth()) - (0d)));
views.get("lbllg").vw.setLeft((int)((views.get("bsubmit").vw.getLeft())));
views.get("lbllg").vw.setWidth((int)((views.get("bsubmit").vw.getWidth())));
views.get("lbllg").vw.setTop((int)((views.get("bsubmit").vw.getTop() + views.get("bsubmit").vw.getHeight())+(2d / 100 * height)));
views.get("plogin").vw.setHeight((int)((views.get("lbllg").vw.getTop() + views.get("lbllg").vw.getHeight())+(30d / 100 * height)));
views.get("plogin").vw.setTop((int)((50d / 100 * height) - (views.get("plogin").vw.getHeight() / 2)));
views.get("breg").vw.setTop((int)((95d / 100 * height) - (views.get("breg").vw.getHeight())));
views.get("breg").vw.setLeft((int)((50d / 100 * width) - (views.get("breg").vw.getWidth() / 2)));
views.get("breg").vw.setLeft((int)((views.get("plogin").vw.getLeft())));
views.get("breg").vw.setWidth((int)((views.get("plogin").vw.getWidth())));
views.get("breg").vw.setHeight((int)((views.get("bsubmit").vw.getHeight())));

}
public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("gifmain").vw.setTop((int)((50d / 100 * height) - (views.get("gifmain").vw.getHeight() / 2)));
views.get("gifmain").vw.setLeft((int)((50d / 100 * width) - (views.get("gifmain").vw.getWidth() / 2)));

}
}