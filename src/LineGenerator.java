
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.layout.Pane;
import javafx.scene.control.*;
import javafx.geometry.Pos;

public class LineGenerator extends Application {

	// GUI variables
	BorderPane root = new BorderPane();
	HBox hBox = new HBox();
	Canvas canvas = new Canvas(1350, 750);
	Button btn = new Button("Simple Scan Conversion");
	Button btn1 = new Button("Bresenhams Line Scan Conversion");
	Button btn2 = new Button("Clear");
	TextField txt = new TextField();
	Label label = new Label("Number of Lines to generate: ");
	

	// TODO : Input Box & Submit Button
	// TODO : Timing Functions & results

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@SuppressWarnings("restriction")
	@Override
	public void start(Stage primaryStage) throws Exception {

		root.setCenter(canvas);
		root.setBottom(hBox);
		hBox.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(15));
		hBox.setPadding(new Insets(15));

		// ControlPane Nodes
		hBox.getChildren().add(label);
		hBox.getChildren().add(txt);
		hBox.getChildren().add(btn);
		hBox.getChildren().add(btn1);
		hBox.getChildren().add(btn2);

		// Call Line Function when pressed
		btn.setOnAction(e -> {
			//Number or lines to generate
			int n = Integer.parseInt(txt.getText());
			
			//Log System Time required to draw N lines
			long time = System.currentTimeMillis();
			
			//Generate endpoints for N lines and draw them
			for (int i = 0; i < n; i++) {
				int x1 = (int) (Math.random() * 1350);
				int y1 = (int) (Math.random() * 750);
				int x2 = (int) (Math.random() * 1350);
				int y2 = (int) (Math.random() * 750);

				bhm_line(x1, y1, x2, y2);
				primaryStage.show();

			}
			time = System.currentTimeMillis() - time;
			String result = "Bresenham's Line Scan Conversion took [" + time + "] milliseconds to draw [" + n +"] lines" ;
			System.out.println(result);
		});

		// Call Line Function when pressed
				btn1.setOnAction(e -> {
					//Number or lines to generate
					int n = Integer.parseInt(txt.getText());
					
					//Log System Time required to draw N lines
					long time = System.currentTimeMillis();
					
					//Generate endpoints for N lines and draw them
					for (int i = 0; i < n; i++) {
						int x1 = (int) (Math.random() * 1350);
						int y1 = (int) (Math.random() * 750);
						int x2 = (int) (Math.random() * 1350);
						int y2 = (int) (Math.random() * 750);
						
						simpleLine(x1, y1, x2, y2);
						primaryStage.show();

					}
					time = System.currentTimeMillis() - time;
					String result = "Simple Line Scan Conversion took [" + time + "] milliseconds to draw [" + n +"] lines" ;
					System.out.println(result);
				});
		
		btn2.setOnAction(e -> {
			canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		});

		
		bhm_line(403, 85, 366, 662);
		// simpleLine(403,85,366,662);
		// simpleLine(377,224,262,551);

		// Initialize and Show Scene
		Scene scene = new Scene(root, 1500, 900);
		primaryStage.setTitle("TEA Test Display");
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();

	}

	public void simpleLine(int x0, int y0, int x1, int y1) {
		double dX = x0 - x1;
		double dY = y0 - y1;
		double m;
		double m2; // slope in terms of X instead of Y
		
		if (dX == 0) {
			m = 0;
			m2 = m;
		} else {
			m = dY / dX;
			m2 = dX / dY;
		}
		dX = Math.abs(dX);
		dY = Math.abs(dY);
		int x = x0;
		int y = y0;

		if (dX > dY) {
			for (int i = 0; i < (dX - 1); i++) {
				x = (int) (x0 + i);
				y = (int) ((m * i) + y0);
				pixel(x, y);

			}
		} else {
			for (int i = 0; i <= (dY - 1); i++) {
				y = (int) (y0 + i);
				x = (int) ((m2 * i) + x0);
				pixel(x, y);
			}

		}
		
	}

	// TODO : Remove this function
	public void pixel(int x, int y) {
		canvas.getGraphicsContext2D().getPixelWriter().setColor(x, y, Color.BLACK);
	}

	// Implementation of Bresenham's Line Scan Conversion Algorithm
	// Source Code provided by StackOverflow User 'Avi'
	// https://stackoverflow.com/questions/10060046/drawing-lines-with-bresenhams-line-algorithm#16405254
	// Accessed 2.1.2020
	void bhm_line(int x1, int y1, int x2, int y2) {
		int x, y, dx, dy, dx1, dy1, px, py, xe, ye, i;
		dx = x2 - x1;
		dy = y2 - y1;
		dx1 = Math.abs(dx);
		dy1 = Math.abs(dy);
		px = 2 * dy1 - dx1;
		py = 2 * dx1 - dy1;
		if (dy1 <= dx1) {
			if (dx >= 0) {
				x = x1;
				y = y1;
				xe = x2;
			} else {
				x = x2;
				y = y2;
				xe = x1;
			}
			pixel(x, y);
			for (i = 0; x < xe; i++) {
				x = x + 1;
				if (px < 0) {
					px = px + 2 * dy1;
				} else {
					if ((dx < 0 && dy < 0) || (dx > 0 && dy > 0)) {
						y = y + 1;
					} else {
						y = y - 1;
					}
					px = px + 2 * (dy1 - dx1);
				}

				pixel(x, y);
			}
		} else {
			if (dy >= 0) {
				x = x1;
				y = y1;
				ye = y2;
			} else {
				x = x2;
				y = y2;
				ye = y1;
			}
			pixel(x, y);
			for (i = 0; y < ye; i++) {
				y = y + 1;
				if (py <= 0) {
					py = py + 2 * dx1;
				} else {
					if ((dx < 0 && dy < 0) || (dx > 0 && dy > 0)) {
						x = x + 1;
					} else {
						x = x - 1;
					}
					py = py + 2 * (dx1 - dy1);
				}

				pixel(x, y);
			}
		}
	}

}
