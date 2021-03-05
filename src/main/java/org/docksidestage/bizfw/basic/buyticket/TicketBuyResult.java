package org.docksidestage.bizfw.basic.buyticket;

/**
 * チケットのお釣り、料金情報を保持する
 *
 * @author zak
 */
public class TicketBuyResult {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int change; // お釣り
    private final int price; // チケットの料金
    private Ticket ticket;

    public TicketBuyResult(int change, int price) {
        this.change = change;
        this.price = price;
        this.ticket = new Ticket(price);
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public Ticket getTicket() {
        return ticket;
    }

    public int getChange() {
        return change;
    }
}
