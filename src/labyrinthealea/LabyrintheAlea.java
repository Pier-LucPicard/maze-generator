/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrinthealea;

/**
 *
 * @author Pier-Luc
 */
public class LabyrintheAlea {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        //new Ui();
       
         int x = 250;
         int y = 250;
         Maze maze=new Maze(x, y);
         //maze.affichage();
         maze.generate("test-");
         maze.generate("essaie");
       

         System.out.println( "Image created." );
        //System.out.println(""+power(7,4));
        //System.out.println(""+Fibonaci(5));
        // TODO code application logic here
    }



}
