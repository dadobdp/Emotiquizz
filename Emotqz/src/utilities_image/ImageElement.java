package utilities_image;

public class ImageElement {
	
	private String immagine;
	private int id;

	public ImageElement(String immagine, int id) {
		super();
		this.immagine = immagine;
		this.id=id;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
