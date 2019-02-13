/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientSide;

/**
 *
 * @author rahma
 */
public interface UserStatusListener {
    public void online (String userEmail );
    public void offline(String userEmail);
    
}
