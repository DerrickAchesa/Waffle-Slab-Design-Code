/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package waffle.slab;
import java.util.Scanner;

public class WaffleDesigner {
    double lx,ly,la;
    double fck,fyk;
    double deadLoad,liveLoad;
    double beamWidth,depth;
    //String panelType;
    
    
    public void setLx(double lx) {
        this.lx = lx;
    }
    
    public void setLy(double ly) {
        this.ly = ly;
    }
    
    public void setLa(double la) {
        this.la = la;
    }
    
    public void setFck(double fck) {
        this.fck = fck;
    }
    
    public void setFyk(double fyk) {
        this.fyk = fyk;
    }
    
    public void setLiveLoad(double liveLoad) {
        this.liveLoad = liveLoad;
    }
    
    public void setDeadLoad(double deadLoad){
        this.deadLoad = deadLoad;
    }
    
    public void setBeamWidth (double beamWidth) {
        this.beamWidth = beamWidth;
    }
    
    public void setDepth(double depth) {
        this.depth = depth;
    }
    
    //This is the part of the program that grabs user inputs
    public void grabInputs() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter LX : ");
        lx = scanner.nextDouble();
        System.out.println("Enter LY : ");
        ly = scanner.nextDouble();
        System.out.println("Enter Fck : ");
        fck = scanner.nextDouble();
        System.out.println("Enter Fyk : ");
        fyk = scanner.nextDouble();
        System.out.println("Enter Liveload : ");
        liveLoad = scanner.nextDouble();
        System.out.println("Enter Deadload : ");
        deadLoad = scanner.nextDouble();
        System.out.println("Enter Beamwidth : ");
        beamWidth = scanner.nextDouble();
        System.out.println("Enter Depth : ");
        depth = scanner.nextDouble();
        //System.out.println("Enter Panel Type : ");
        //panelType = scanner.nextLine();
    }
    
    double calcUltimateLoad() {
        return (1.35 * deadLoad + 1.5 * liveLoad);
    }
    
    double midspanPositiveMoment() {
        double ratio = ly/lx; 
        
        double bsx = Reader.readShortSpan85("Interior Panel", "Positive moment at midspan", ratio);
        double n = calcUltimateLoad();
        return (bsx * n * Math.pow(lx, 2));
    }
    
    double momentAtMidspan() {
        return (beamWidth * midspanPositiveMoment());
    }
    
    double fckRatioMidspan() {
        double M = momentAtMidspan() * Math.pow(10, 6);
        return (M / (beamWidth * 1000 * Math.pow(depth, 2) * fck));
    }
    
    double ASMidspan() {
        double M = momentAtMidspan() * Math.pow(10, 6);
        return (M/(0.87 * fyk * la * depth));
    }
    
    double supportNegativeMoment() {
        double ratio = ly/lx; 
        
        double bsx = Reader.readShortSpan85("Interior Panel", "Negative Moment At Continuous edge", ratio);
        double n = calcUltimateLoad();
        return (bsx * n * Math.pow(lx, 2));
    }
    
    double momentAtSupport() {
        return (beamWidth * supportNegativeMoment());
    }
    
    double fckRatioSupport() {
        double M = momentAtSupport() * Math.pow(10, 6);
        return (M / (beamWidth * 1000 * Math.pow(depth, 2) * fck));
    }
    
    double ASSupport() {
        double M = momentAtSupport() * Math.pow(10, 6);
        return (M/(0.87 * fyk * la * depth));
    }
    
    double rhoMidspan() {
        double AS = ASMidspan();
        return ((100 * AS)/(beamWidth * depth * 100));
    }
    
    
    public String designWaffle() {
        String output = "<html><body>";
        String mMidspan = "<p>Moment at Midspan is " + Double.toString(momentAtMidspan()) + " kNm</p>";
        String fckRatioMidspan = "<p>Fck Ratio at Midspan is " + Double.toString(fckRatioMidspan()) + "</p>";
        String ASMidspan = "<p>AS at Midspan is " + Double.toString(ASMidspan()) + " mm^2</p>";
        String mSupport = "<p>Moment at Support is " + Double.toString(momentAtSupport()) + " kNm </p>";
        String fckRatioSupport = "<p>Fck Ratio at Support is " + Double.toString(fckRatioSupport()) + "</p>";
        String ASSupport = "<p>AS at Support is " + Double.toString(ASSupport()) + " mm^2</p>";
        String rhoMidspan = "<p>Rho at Midspan is " + Double.toString(rhoMidspan()) + " %</p>";
        output += mMidspan;
        output += fckRatioMidspan;
        output += ASMidspan;
        output += mSupport;
        output += fckRatioSupport;
        output += ASSupport;
        output += rhoMidspan;
        output += "</body></html>";
        return output;
    }
     
}
