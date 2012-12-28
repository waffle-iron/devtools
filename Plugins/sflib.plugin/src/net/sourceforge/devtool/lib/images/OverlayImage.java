package net.sourceforge.devtool.lib.images;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jface.resource.CompositeImageDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

/**
 * @author michel
 *
 *History of Changes:
 *
 *  $Log: OverlayImage.java,v $
 *  Revision 1.1  2005/04/03 10:38:51  tmichel
 *  new structure as a mix of j2ee best praxis and maven
 *
 *  Revision 1.5  2004/04/06 10:31:28  tmichel
 *  changes for new image api
 *
 *  Revision 1.4  2003/07/13 22:38:32  tmichel
 *  structure changed:
 *  - lib for core functions
 *  - support plugin
 *  - wizard plugin
 *
 *  Revision 1.1  2003/04/06 18:54:11  tmichel
 *  new version without lib plugin
 *
 *  Revision 1.2  2003/02/23 17:20:51  tmichel
 *  new images added and handling via url implements
 *
 *  Revision 1.1  2003/02/02 21:18:01  tmichel
 *  project nature changed to plugin nature
 *  image methods added
 *
 * 
 */
public class OverlayImage extends CompositeImageDescriptor {

	protected final Logger logger = Logger.getLogger(getClass().getName());

	protected ImageDescriptor overlayImageDescriptor = null;
	protected Image baseImage = null;
	protected String imagePath = null;
	protected int width = 0;
	protected int height = 0;

	/**
	 * Constructor for OverlayImage.
	 */
	public OverlayImage(
		final Image baseImage,
		final ImageDescriptor pluginImages,
		final String imagePath) {
		super();
		this.baseImage = baseImage;
		this.imagePath = imagePath;
		this.overlayImageDescriptor = pluginImages;
	} //OverlayImage(Image,String)

	/**
	 * @see org.eclipse.jface.resource.CompositeImageDescriptor#drawCompositeImage(int, int)
	 */
	protected void drawCompositeImage(int width, int height) {
		drawImage(baseImage.getImageData(), 0, 0);
		try {
			ImageData overlayImageData = overlayImageDescriptor.getImageData();
			drawImage(overlayImageData, 0, 0);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, ex.getMessage());
		}
		final Rectangle bounds = baseImage.getBounds();
		this.width = bounds.width;
		this.height = bounds.height;
	} //drawCompositeImage(int,int)

	/**
	 * @see org.eclipse.jface.resource.CompositeImageDescriptor#getSize()
	 */
	protected Point getSize() {
		Point size = new Point(16, 16);
		return size;
	} //getSize()

} //OverlayImage
