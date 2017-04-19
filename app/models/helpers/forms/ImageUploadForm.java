package models.helpers.forms;

import models.BaseModel;

import java.util.UUID;

/**
 * The type Image upload form.
 */
public class ImageUploadForm extends BaseModel {

	private UUID itemId;
	private String imageType;
	private String extension;

	/**
	 * Instantiates a new Image upload form.
	 */
	public ImageUploadForm() {}

	/**
	 * Gets item id.
	 *
	 * @return the item id
	 */
	public UUID getItemId() { return itemId; }

	/**
	 * Sets item id.
	 *
	 * @param itemId the item id
	 */
	public void setItemId(UUID itemId) { this.itemId = itemId; }

	/**
	 * Gets image type.
	 *
	 * @return the image type
	 */
	public String getImageType() { return imageType; }

	/**
	 * Sets image type.
	 *
	 * @param imageType the image type
	 */
	public void setImageType(String imageType) { this.imageType = imageType; }

	/**
	 * Gets extension.
	 *
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * Sets extension.
	 *
	 * @param extension the extension
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}
}
