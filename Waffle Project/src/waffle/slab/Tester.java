/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package waffle.slab;


public class Tester {
    
    static void testReadShortSpan85() {
        System.out.println(Reader.readShortSpan85("Interior Panel",
                "nEGATIVE moment at continuous edge", 1.25));
    }
    
    static void testLongSpan85() {
        System.out.println(Reader.readLongSpan85("one short EDGE discontinuous",
                "positive moment at midspan"));
    }
    
    static void test83() {
        System.out.println(Reader.read83(50));
    }
    
    static void test82() {
        System.out.println(Reader.read82(1, 600));
    }
    
    static void testFckRatioMidspan() {
        WaffleDesigner designer = new WaffleDesigner();
        designer.grabInputs();
        System.out.println(designer.fckRatioMidspan());
    }
    
    static void testASMidspan() {
        WaffleDesigner designer = new WaffleDesigner();
        designer.grabInputs();
        System.out.println(designer.ASMidspan());
    }
    
    static void testFckRatioSupport() {
        WaffleDesigner designer = new WaffleDesigner();
        designer.grabInputs();
        System.out.println(designer.fckRatioSupport());
    }
    
    static void testASSupport() {
        WaffleDesigner designer = new WaffleDesigner();
        designer.grabInputs();
        System.out.println(designer.ASSupport());
    }
    
    static void testRhoMidspan() {
        WaffleDesigner designer = new WaffleDesigner();
        designer.grabInputs();
        System.out.println(designer.rhoMidspan());
    }   
    
}
