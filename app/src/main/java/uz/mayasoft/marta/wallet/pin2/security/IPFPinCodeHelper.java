package uz.mayasoft.marta.wallet.pin2.security;

import android.content.Context;

import uz.mayasoft.marta.wallet.pin2.security.callbacks.PFPinCodeHelperCallback;


public interface IPFPinCodeHelper {

    void encodePin(Context context, String pin, PFPinCodeHelperCallback<String> callBack);

    void checkPin(Context context, String encodedPin, String pin, PFPinCodeHelperCallback<Boolean> callback);


    void delete(PFPinCodeHelperCallback<Boolean> callback);

    void isPinCodeEncryptionKeyExist(PFPinCodeHelperCallback<Boolean> callback);

}
