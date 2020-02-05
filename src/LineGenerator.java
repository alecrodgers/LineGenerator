

import javafx.scene.paint.Color;
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
	Canvas canvas = new Canvas(1350,750);
	Button btn = new Button("Generate Lines");
	Button btn1 = new Button("Clear");
	TextField txt = new TextField();
	Label label = new Label("Number of Lines to generate: ");

	
	//TODO : Input Box & Submit Button
	//TODO : Timing Functions & results
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@SuppressWarnings("restriction")
	@Override
	public void start(Stage primaryStage) throws Exception {

		//root.setLeft(hBox);
		root.setCenter(canvas);
		root.setBottom(hBox);
		hBox.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(15));
		
		//ControlPane Nodes
		hBox.getChildren().add(label);
		hBox.getChildren().add(txt);
		hBox.getChildren().add(btn);
		hBox.getChildren().add(btn1);
		
		//Call Line Function when pressed
		btn.setOnAction(e->{
			for(int i = 0; i < Integer.parseInt(txt.getText()); i++) {
				int x1 = (int)(Math.random()*1350);
				int y1 = (int)(Math.random()*750);
				int x2 = (int)(Math.random()*1350);
				int y2 = (int)(Math.random()*750);
				
				//simpleLine(x1,y1,x2,y2);
				bresenhamLine(x1,y1,x2,y2);
		}
		});
		
		btn1.setOnAction(e->{
			canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		});
		
		//brez(403,85,366,662);
		//drawline(403,85,366,662);
		bresenhamLine(403,85,366,662);
		//simpleLine(403,85,366,662);
		//simpleLine(377,224,262,551);
		//pixel(1,1);
		
		// Initialize and Show Scene
		Scene scene = new Scene(root, 1400, 800);
		primaryStage.setTitle("TEA Test Display");
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();

	}
	
	
	
	
	
	
	

	public void simpleLine(int x0, int y0, int x1, int y1){
		double dX = x0 - x1;
		double dY = y0 - y1;
		double m;
		double m2; // slope in terms of X instead of Y
		if(dX == 0){
			m = 0;
			m2 = m;
		} else {
			m = dY/dX;
			m2 = dX/dY; 
		}
		dX = Math.abs(dX);
		dY = Math.abs(dY);
		int x = x0;
		int y = y0;
		

		//Debug Prints
		System.out.println(0 < (dX-1));
		System.out.println("dX = " + dX);
		System.out.println("dY = " + dY);
		System.out.println("X0 = " + x0);
		System.out.println("Y0 = " + y0);
		System.out.println("X1 = " + x1);
		System.out.println("Y1 = " + y1);

		if(dX > dY) {
			for(int i = 0; i < (dX - 1); i++) {
				x = (int)(x0 + i);
				y = (int)((m*i) + y0);
				pixel(x,y);

			}
		} else {
			for(int i = 0; i <= (dY - 1); i++) { 
				y = (int)(y0 + i);
				x = (int)((m2*i) + x0);
				pixel(x,y);
			}
			
			
		}
		//Debug Prints
		System.out.println("X = " + x);
		System.out.println("Y = " + y);

	}

	
			//TODO : Remove this function
			public void pixel(int x, int y) {
				canvas.getGraphicsContext2D().getPixelWriter().setColor(x, y, Color.BLACK);
				
			}
			
			
			
			//TODO : Implement Bresenhams Algorithm
			void bresenhamLine(int x0, int y0, int x1, int y1) {
				double dX = x0 - x1;
				double dY = y0 - y1;
				double e = 2 * dX - dY;
				int inc1 = (int)(2 * dY);
				int inc2 = (int)-(2 * (dY-dX));
				int x,y;
				if(x0 < x1) {
					x = x0;
					y = y0;
				} else {
					x = x1;
					y = y1;
				}
				
				System.out.println("M = " + (dY/dX));
				System.out.println("inc1 = " + inc1);
				System.out.println("inc2 = " + inc2);

				dX = Math.abs(dX);
				dY = Math.abs(dY);
				
				
				//Debug Prints
//				System.out.println(0 < (dX-1));
				System.out.println("dX = " + dX);
				System.out.println("dY = " + dY);
//				System.out.println("X0 = " + x0);
//				System.out.println("Y0 = " + y0);
//				System.out.println("X1 = " + x1);
//				System.out.println("Y1 = " + y1);
//				
				
				if(dX > dY) {
					
					while(x < x1) {
						pixel(x,y);
						if(e < 0) {
							e = e+inc1;
						} else {
							y = y+1;
							e = e+inc2;
						}	
						x = x+1;
					}
				} else {
					while(y < y1) {
						pixel(x,y);
						if(e < 0) {
							e = e+inc1;
						} else {
							x = x+1;
							e = e+inc2;
						}	
						y = y+1;
					}
					//if Line is Vertical
				}
				
				//Debug Prints
				System.out.println("Last X = " + x);
				System.out.println("Last Y = " + y + "\n==================");
			}
			
			
			void brez(int x1, int y1, int x2, int y2)
			{
				int m_new = 2 * (y2 - y1); 
				   int slope_error_new = m_new - (x2 - x1); 
				   for (int x = x1, y = y1; x <= x2; x++) 
				   { 
				     pixel(x,y);
				  
				      // Add slope to increment angle formed 
				      slope_error_new += m_new; 
				  
				      // Slope error reached limit, time to 
				      // increment y and update slope error. 
				      if (slope_error_new >= 0) 
				      { 
				         y++; 
				         slope_error_new  -= 2 * (x2 - x1); 
				      } 
				   } 
			}
			
			
			void drawline(int x0, int y0, int x1, int y1)
			{
			    int dx, dy, p, x, y;

			    dx=x1-x0;
			    dy=y1-y0;

			    x=x0;
			    y=y0;

			    p=2*dy-dx;

			    while(x<x1)
			    {
			        if(p>=0)
			        {
			            pixel(x,y);
			            y=y+1;
			            p=p+2*dy-2*dx;
			        }
			        else
			        {
			            pixel(x,y);
			            p=p+2*dy;
			        }
			        x=x+1;
			    }
			}
			

		}
