// 211699665 Ilai Pingle

package Interfaces;

/**
 * HitNotifier interface.
 * this interface will be used by objects that want to notify others of hit events.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     *
     * @param hl the HitListener to add.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the HitListener to remove.
     */
    void removeHitListener(HitListener hl);
}

