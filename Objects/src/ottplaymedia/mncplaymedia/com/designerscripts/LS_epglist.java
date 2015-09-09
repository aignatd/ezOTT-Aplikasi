package ottplaymedia.mncplaymedia.com.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_epglist{

public static void LS_320x480_1(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("patas").vw.setLeft((int)(0d));
views.get("patas").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("patas").vw.setTop((int)(0d));
views.get("patas").vw.setHeight((int)((9d / 100 * height) - (0d)));
views.get("svepg").vw.setTop((int)((views.get("patas").vw.getHeight())));
views.get("svepg").vw.setHeight((int)((100d / 100 * height) - ((views.get("patas").vw.getHeight()))));
views.get("svepg").vw.setLeft((int)(0d));
views.get("svepg").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("ivchaname").vw.setHeight((int)((views.get("patas").vw.getHeight())-(1d / 100 * height)));
views.get("ivchaname").vw.setWidth((int)((views.get("patas").vw.getHeight())+(2d / 100 * width)));
views.get("ivchaname").vw.setLeft((int)((96d / 100 * width) - (views.get("ivchaname").vw.getWidth())));
views.get("ivchaname").vw.setTop((int)((views.get("patas").vw.getHeight())/2d - (views.get("ivchaname").vw.getHeight() / 2)));
views.get("ivbackepg").vw.setLeft((int)((4d / 100 * width)));
views.get("ivbackepg").vw.setHeight((int)((views.get("patas").vw.getHeight())/1.5d));
views.get("ivbackepg").vw.setWidth((int)((views.get("patas").vw.getHeight())/1.5d));
views.get("ivbackepg").vw.setTop((int)((views.get("patas").vw.getHeight())/2d - (views.get("ivbackepg").vw.getHeight() / 2)));

}
public static void LS_480x320_1(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("patas").vw.setLeft((int)(0d));
views.get("patas").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("patas").vw.setTop((int)(0d));
views.get("patas").vw.setHeight((int)((13d / 100 * height) - (0d)));
views.get("svepg").vw.setTop((int)((views.get("patas").vw.getHeight())));
views.get("svepg").vw.setHeight((int)((100d / 100 * height) - ((views.get("patas").vw.getHeight()))));
views.get("svepg").vw.setLeft((int)(0d));
views.get("svepg").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("ivchaname").vw.setLeft((int)(0d));
views.get("ivchaname").vw.setHeight((int)((views.get("patas").vw.getHeight())));
views.get("ivchaname").vw.setWidth((int)((views.get("patas").vw.getHeight())));
views.get("ivchaname").vw.setLeft((int)((98d / 100 * width) - (views.get("ivchaname").vw.getWidth())));
views.get("ivbackepg").vw.setLeft((int)((4d / 100 * width)));
views.get("ivbackepg").vw.setHeight((int)((views.get("patas").vw.getHeight())/1.5d));
views.get("ivbackepg").vw.setWidth((int)((views.get("patas").vw.getHeight())/1.5d));
views.get("ivbackepg").vw.setTop((int)((views.get("patas").vw.getHeight())/2d - (views.get("ivbackepg").vw.getHeight() / 2)));

}
public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("gifepg").vw.setTop((int)((50d / 100 * height) - (views.get("gifepg").vw.getHeight() / 2)));
views.get("gifepg").vw.setLeft((int)((50d / 100 * width) - (views.get("gifepg").vw.getWidth() / 2)));

}
}