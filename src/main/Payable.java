package main;

import model.Amount;

/**
 *
 * @author ignac
 */
public interface Payable {

    public abstract boolean pay(Amount amount);
}
