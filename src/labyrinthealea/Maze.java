/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrinthealea;


import java.awt.Color;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;



import java.io.File;



import java.io.IOException;



import javax.imageio.IIOImage;

import javax.imageio.ImageIO;

import javax.imageio.ImageWriteParam;

import javax.imageio.ImageWriter;

import javax.imageio.plugins.jpeg.JPEGImageWriteParam;

import javax.imageio.stream.ImageOutputStream;


import java.util.Random;

/**
 *
 * @author Pier-Luc
 */
public class Maze {

    public String[] mazeTxt;
    /**
     *
     */
    public int[] indice;
    /**
     *
     */
    public int[] casse;
    /**
     * Horizontal lenght of the maze in number of square.
     */
    public int x = 0;
    /**
     * Vertical lenght of the maze in number of square.
     */
    public int y = 0;

    /**
     * Constructor of the maze Class
     * @param x Horizontal lenght of the maze in: number of square.
     * @param y Vertical lenght of the maze in: number of square.
     */
    public Maze(int x, int y) {
        this.x = x;
        this.y = y;
        
        indice = new int[x * y];
        for (int i = 0; i < x * y; i++) {
            indice[i] = i;
        }
        casse = new int[x * y * 5];
        generation(x, y);
    }

    /**
     *  Method that prints the maze in the consol output.
     */
    public void affichage() {
        mazeTxt=new String[2*y+1];
        int conteur=0;
        for (int j = 0; j < x * y; j = j + x) {
            
            for (int i = 0; i < x; i++) {
                if (casse[((i + j) * 5) + 1] == 0) {
                    mazeTxt[conteur]=mazeTxt[conteur]+" + -";
                    System.out.print(" + -");
                } else {
                    mazeTxt[conteur]=mazeTxt[conteur]+" +  ";
                    System.out.print(" +  ");
                }
                if (i == x - 1) {
                    mazeTxt[conteur]=mazeTxt[conteur]+" |";
                    System.out.print(" |");
                }


            }
            conteur++;
            System.out.println("");
            for (int i = 0; i < x; i++) {

                if (casse[((i + j) * 5) + 4] == 0) {
                    mazeTxt[conteur]=mazeTxt[conteur]+" | ";
                    System.out.print(" | ");
                } else {
                    mazeTxt[conteur]=mazeTxt[conteur]+"   ";
                    System.out.print("   ");
                }
                if (i == 0 && j == 0) {
                    mazeTxt[conteur]=mazeTxt[conteur]+"D";
                    System.out.print("D");
                } else if (i == x - 1 && j == x * y - x) {
                    mazeTxt[conteur]=mazeTxt[conteur]+"A";
                    System.out.print("A");

                } else {
                    mazeTxt[conteur]=mazeTxt[conteur]+" ";
                    System.out.print(" ");
                }
                if (i == x - 1) {
                    mazeTxt[conteur]=mazeTxt[conteur]+" |";
                    System.out.print(" |");
                }


            }
            conteur++;
            System.out.println("");
            if (j == (y - 1) * x) {
                for (int i = 0; i < x; i++) {
                    if (casse[((i + j) * 5) + 2] == 0) {
                        mazeTxt[conteur]=mazeTxt[conteur]+" + -";
                        System.out.print(" + -");
                    } else {
                        mazeTxt[conteur]=mazeTxt[conteur]+" +  ";
                        System.out.print(" +  ");
                    }
                    if (i == x - 1) {
                        mazeTxt[conteur]=mazeTxt[conteur]+" +";
                        System.out.print(" +");
                    }


                }
                conteur++;
                System.out.println("");
            }
        }



    }

    public String[] getMazeTxt() {
        affichage();
        return mazeTxt;
    }
    
    

    /**
     *
     * @param x
     * @param y
     */
    public void generation(int x, int y) {
        Random rd = new Random();



        int nbPorte = 0;
        //int valeur = 0;


        while (nbPorte != x * y - 1) {
            // valeur = 0;
            int r = rd.nextInt(x * y);


            int c = rd.nextInt(4) + 1;

            if (c == 4) {
                if ((r - 1) % x != x - 1 && (r - 1) >= 0) {
                    if (indice[r - 1] != indice[r]) {
                        int z = indice[r];

                        for (int i = 0; i < indice.length; i++) {
                            if (indice[i] == z) {
                                indice[i] = indice[r - 1];
                            }
                        }

                        indice[r] = indice[r - 1];



                        casse[r * 5 + c] = 1;
                        casse[(r - 1) * 5 + (c - 1)] = 1;
                        //System.out.println("porte ouset en r:" + r);
                        nbPorte++;
                    }
                }

            }
            if (c == 2) {
                if ((r + x) < x * y) {
                    if (indice[r] != indice[r + x]) {
                        int z = indice[r + x];

                        for (int i = 0; i < indice.length; i++) {
                            if (indice[i] == z) {
                                indice[i] = indice[r];
                            }
                        }

                        indice[r + x] = indice[r];



                        casse[r * 5 + c] = 1;
                        casse[(r + x) * 5 + (c - 1)] = 1;
                        nbPorte++;
                        //System.out.println("porte sud en r:" + r);
                    }

                }
            }
            if (c == 1) {
                if ((r - x) >= 0) {

                    if (indice[r] != indice[r - x]) {
                        int z = indice[r];

                        for (int i = 0; i < indice.length; i++) {
                            if (indice[i] == z) {
                                indice[i] = indice[r - x];
                            }
                        }

                        indice[r] = indice[r - x];



                        casse[r * 5 + c] = 1;
                        casse[(r - x) * 5 + (c + 1)] = 1;
                        //System.out.println("porte nord en r:" + r);
                        nbPorte++;
                    }

                }
            }
            if (c == 3) {
                if ((r + 1) % x != 0 && (r + 1) < x * y) {
                    if (indice[r + 1] != indice[r]) {
                        int z = indice[r + 1];

                        for (int i = 0; i < indice.length; i++) {
                            if (indice[i] == z) {
                                indice[i] = indice[r];
                            }
                        }
                        indice[r + 1] = indice[r];
                        casse[r * 5 + c] = 1;
                        casse[(r + 1) * 5 + (c + 1)] = 1;
                        //System.out.println("porte est en r:" + r);
                        nbPorte++;
                    }
                }

            }






        }
    }

    /**
     *
     * @param prefix
     * @return
     * @throws Exception
     */
    public String generate(String prefix) throws Exception {



        Random random = new Random(56743793);
        Color c = new Color(255, 0, 255, 255);



        int h = 0;
        int l = 0;


        //image block size in pixels, 1 is 1px, use smaller values for 

        //greater granularity

        int PIX_SIZE = 1;



        //image size in pixel blocks

        int X = x * 2 + 1;

        int Y = y * 2 + 1;



        BufferedImage bi = new BufferedImage(PIX_SIZE * X, PIX_SIZE * Y,
                BufferedImage.TYPE_INT_BGR);


        Graphics2D g = (Graphics2D) bi.getGraphics();


        String filename = prefix + "img.jpg";



        System.out.println(casse.length);

        int conteurN = 0;
        int conteurE = 0;


        for (int j = 0; j < Y; j++) {

            for (int i = 0; i < X; i++) {


                h = i * PIX_SIZE;

                l = j * PIX_SIZE;

                System.out.print(conteurN + " et h=" + h + " ");


                if (j % 2 == 0) {
                    if (i % 2 == 1) {
                        if (conteurN * 5 < casse.length) {
                            if (casse[conteurN * 5 + 1] == 1) {
                                g.setColor(Color.WHITE);
                            } else {
                                g.setColor(Color.BLACK);
                            }
                            conteurN++;
                        }
                    } else {
                        g.setColor(Color.BLACK);
                    }
                } else {
                    //Condition pour est et ouest
                    if (i % 2 == 1) {
                        g.setColor(Color.WHITE);
                    } else {
                        if (i % (x * 2 + 1) > 0) {
                            if (casse[conteurE * 5 + 3] == 1) {
                                g.setColor(Color.WHITE);
                            } else {
                                g.setColor(Color.BLACK);
                            }
                            conteurE++;
                        } else {
                            g.setColor(Color.BLACK);
                        }
                    }
                }


                //fil the rectangles with the pixel blocks in chosen color

                g.fillRect(h, l, PIX_SIZE, PIX_SIZE);





            }//end for i

            System.out.println("");
        }//end for j




        g.dispose();


        try {
            // retrieve image

            File outputfile = new File("saved.png");
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {
        }
        // saveToFile(bi, new File(filename));

        return filename;



    }

    /**
     *
     * @param img
     * @param file
     * @throws IOException
     */
    public static void saveToFile(BufferedImage img, File file) throws IOException {



        ImageWriter writer = null;

        java.util.Iterator iter = ImageIO.getImageWritersByFormatName("jpg");



        if (iter.hasNext()) {

            writer = (ImageWriter) iter.next();

        }



        ImageOutputStream ios = ImageIO.createImageOutputStream(file);

        writer.setOutput(ios);



        ImageWriteParam param = new JPEGImageWriteParam(java.util.Locale.getDefault());



        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);

        param.setCompressionQuality(0.98f);



        writer.write(null, new IIOImage(img, null, null), param);


    }
}
