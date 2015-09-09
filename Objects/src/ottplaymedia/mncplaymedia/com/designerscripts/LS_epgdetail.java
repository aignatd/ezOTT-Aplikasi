package ottplaymedia.mncplaymedia.com.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_epgdetail{

public static void LS_320x480_1(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
String _tinggi="";
_tinggi = BA.NumberToString((9d / 100 * height));
views.get("ivepgdet").vw.setWidth((int)(Double.parseDouble(_tinggi)/2d));
views.get("ivepgdet").vw.setLeft((int)((98d / 100 * width) - (views.get("ivepgdet").vw.getWidth())));
views.get("ivepgdet").vw.setHeight((int)(Double.parseDouble(_tinggi)/2d));
views.get("ivepgdet").vw.setTop((int)((Double.parseDouble(_tinggi)-(views.get("ivepgdet").vw.getHeight()))/2d));
views.get("lblepgdet").vw.setLeft((int)((3d / 100 * width)));
views.get("lblepgdet").vw.setWidth((int)((views.get("ivepgdet").vw.getLeft()) - ((3d / 100 * width))));
views.get("lblepgdet").vw.setTop((int)(0d));
views.get("lblepgdet").vw.setHeight((int)(Double.parseDouble(_tinggi) - (0d)));

}
public static void LS_480x320_1(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
String _tinggi="";
_tinggi = BA.NumberToString((11d / 100 * height));
views.get("ivepgdet").vw.setWidth((int)(Double.parseDouble(_tinggi)/2d));
views.get("ivepgdet").vw.setLeft((int)((98d / 100 * width) - (views.get("ivepgdet").vw.getWidth())));
views.get("ivepgdet").vw.setHeight((int)(Double.parseDouble(_tinggi)/2d));
views.get("ivepgdet").vw.setTop((int)((Double.parseDouble(_tinggi)-(views.get("ivepgdet").vw.getHeight()))/2d));
views.get("lblepgdet").vw.setLeft((int)((3d / 100 * width)));
views.get("lblepgdet").vw.setWidth((int)((views.get("ivepgdet").vw.getLeft()) - ((3d / 100 * width))));
views.get("lblepgdet").vw.setTop((int)(0d));
views.get("lblepgdet").vw.setHeight((int)(Double.parseDouble(_tinggi) - (0d)));

}
public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);

}
}