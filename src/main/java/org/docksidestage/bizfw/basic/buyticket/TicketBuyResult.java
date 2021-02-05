package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author zak
 */
public class TicketBuyResult {


    TicketBuyResult() {
    }







    public Ticket getTicket() {
        return new Ticket(price);
    }
}
