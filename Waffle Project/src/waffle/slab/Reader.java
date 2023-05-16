/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package waffle.slab;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files


public class Reader {
    
    static double readShortSpan85(String panelType,String momentType,double ratio) {
        double coefficient = -1;
        try {
          File myObj = new File("C:\\Users\\ADMIN\\Desktop\\5TH Year Project\\Waffle Resources\\table85.txt");
          Scanner myReader = new Scanner(myObj);
          int theIndex = findRatioIndex(ratio);
          while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String foundPanelType = data.split("\t")[0].toUpperCase();
            if(foundPanelType.contains(panelType.toUpperCase())) {
                if(momentType.toUpperCase().contains("NEGATIVE MOMENT AT CONTINUOUS EDGE")) {
                    data = myReader.nextLine();
                    if(theIndex != -1) {
                        coefficient = Double.parseDouble(data.split("\t")[theIndex]);
                    }else {
                        int leastRatioIndex = findLeastRatioIndex(ratio);
                        double x = ratio;
                        double x1 = findRatio(leastRatioIndex -1);
                        double x2 = findRatio(leastRatioIndex);
                        double y1 = Double.parseDouble(data.split("\t")[leastRatioIndex]);
                        double y2 = Double.parseDouble(data.split("\t")[leastRatioIndex + 1]);
                        coefficient = interpolate(x,x1,x2,y1,y2);
                    }
                    break;
                } else if(momentType.toUpperCase().contains("POSITIVE MOMENT AT MIDSPAN")){
                    myReader.nextLine();
                    data = myReader.nextLine();
                    if (theIndex != -1) {
                        coefficient = Double.parseDouble(data.split("\t")[theIndex]);
                    } else {
                        int leastRatioIndex = findLeastRatioIndex(ratio);
                        double x = ratio;
                        double x1 = findRatio(leastRatioIndex - 1);
                        double x2 = findRatio(leastRatioIndex);
                        double y1 = Double.parseDouble(data.split("\t")[leastRatioIndex]);
                        double y2 = Double.parseDouble(data.split("\t")[leastRatioIndex + 1]);
                        coefficient = interpolate(x,x1,x2,y1,y2);
                    }
                    
                    break;
                }
            }
          }
          myReader.close();
        } catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
      return coefficient;
    }
    
    static double readLongSpan85(String panelType,String momentType) {
        double coefficient = -1;
        try {
          File myObj = new File("C:\\Users\\ADMIN\\Desktop\\5TH Year Project\\Waffle Resources\\table85.txt");
          Scanner myReader = new Scanner(myObj);
          while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String foundPanelType = data.split("\t")[0].toUpperCase();
            if(foundPanelType.contains(panelType.toUpperCase())) {
                if(momentType.toUpperCase().contains("NEGATIVE MOMENT AT CONTINUOUS EDGE")) {
                    data = myReader.nextLine();
                    coefficient = Double.parseDouble(data.split("\t")[6]);
                    break;
                } else if(momentType.toUpperCase().contains("POSITIVE MOMENT AT MIDSPAN")){
                    myReader.nextLine();
                    data = myReader.nextLine();
                    coefficient = Double.parseDouble(data.split("\t")[6]);
                    break;
                }
            }
          }
          myReader.close();
        } catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
      return coefficient;
    }
    private static int findRatioIndex(double ratio) {
        int theIndex = -1;
        double [] ratios = {1,1.25,1.5,1.75,2};
        for (int i=0; i<ratios.length;i++) {
            if (ratio == ratios[i]) {
                theIndex = i + 1;
                break;
            }
        }
        return theIndex;
    }
    
    private static int findLeastRatioIndex(double ratio) {
        int theIndex = -1;
        double [] ratios = {1,1.25,1.5,1.75,2};
        for (int i=0; i<ratios.length;i++){
            if (ratio > ratios[i] && ratio < ratios[i + 1]) {
                theIndex = i + 1;
                break;
            }
        }
        return theIndex;
    }
    
    private static double findRatio(int index) {
        double [] ratios = {1,1.25,1.5,1.75,2};
        return ratios[index];
    }
    
    private static double interpolate(double x,double x1,double x2,
            double y1,double y2) {
        double value = y1 + ((x - x1)/(x2 - x1)) * (y2 - y1);
        return value;
    }
    
    public static double read83(double fck) {
        double modFactor = -1;
        double [] fckArr = {25,30,35,40,45,50};
        double [] modFactors = {0.94,1,1.05,1.1,1.14,1.19};
        for (int i = 0; i < fckArr.length; i++) {
            if (fck == fckArr[i]) {
                modFactor = modFactors[i];
                break;
            }
        }
        return modFactor;
    }
    
    //ASK ABOUT TYPE OF depth, INT OR DOUBLE ??
    //ALSO WTF IS K ??
    public static double read82(double rho,int depth) {
        double coefficient = -1;
        int theIndex = find82Index(depth);
        try {
          File myObj = new File("C:\\Users\\ADMIN\\Desktop\\5TH Year Project\\Waffle Resources\\table82.txt");
          Scanner myReader = new Scanner(myObj);
          myReader.nextLine();
          while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            if (!data.split("\t")[0].contains("k")) {
                double foundRho = Double.valueOf(data.split("\t")[0]);
                if (foundRho == rho) {
                    if(theIndex != -1) {
                        coefficient = Double.valueOf(data.split("\t")[theIndex]);
                    }else {
                        //Perform Interpolation
                    }
                }
           }
          }  
          myReader.close();
        } catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
      return coefficient;
    }
    
    private static int find82Index(int depth) {
        int theIndex = -1;
        int [] depths = {200,225,250,300,350,400,500,600,750};
        for (int i=0;i<depths.length;i++) {
            if (depth <= depths[0]) {
                theIndex = 1;
                break;
            }
            if(depth == depths[i]) {
                theIndex = i + 1;
                break;
            }
        }
        return theIndex;
    }
    
    
    
}
