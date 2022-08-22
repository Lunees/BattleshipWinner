
package com.company;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.JPanel;




public class JPanelWithBackground extends JPanel {

	private Image bgImage;

	public JPanelWithBackground() {
		super();
	}

    //  Layout som skall användas.

	public JPanelWithBackground(LayoutManager layout) {
		super(layout);
	}

	 // Doublebuffer laddar bilden i bakgrunden så den är redo när den kallas på().

	public JPanelWithBackground(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
	}


	 //  layout Layout som skall användas.
	 //  isDoubleBuffered Om dubbelbuffering ska användas eller inte.

	public JPanelWithBackground(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// Rita komponenten som vanligt.
		super.paintComponent(g);
		
		// Modifiering: Rita bakgrundsbild ifall den är satt.
		if(bgImage != null)
			g.drawImage(bgImage, 0, 0, null);
	}


	 	// Sätter bakgrundsbilden som ska användas och skalar den till storleken för denna panelen.
	 	// bgImage Bakgrundsbilden som ska användas och skalas.

	public void setImage(Image bgImage) {
		// Sätter bgImage till skalad variant av specificerad bild.
		this.bgImage = GameBoardGUI.scaleImage(bgImage, getWidth(), getHeight());
	}

}
