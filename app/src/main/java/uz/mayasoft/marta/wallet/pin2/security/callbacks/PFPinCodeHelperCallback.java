package uz.mayasoft.marta.wallet.pin2.security.callbacks;


import uz.mayasoft.marta.wallet.pin2.security.PFResult;

public interface PFPinCodeHelperCallback<T> {
    void onResult(PFResult<T> result);
}