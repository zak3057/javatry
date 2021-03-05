package org.docksidestage.bizfw.basic.buyticket;

/**
 * 
 *
 * @author zak
 */
public class TicketBuyResult {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int change; // お釣り
    private final int price; // チケットの料金
    // private boolean alreadyIn;

    public TicketBuyResult(int change, int price) {
        this.change = change;
        this.price = price;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public Ticket getTicket() {
        return new Ticket(price);
    }

    public int getChange() {
        return change;
    }
}
