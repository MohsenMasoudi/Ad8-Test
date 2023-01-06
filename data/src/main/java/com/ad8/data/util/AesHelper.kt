package com.varmi.data.util

import android.util.Base64
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.security.GeneralSecurityException
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import javax.inject.Singleton

@Singleton
class AesHelper {
    private val CIPHER_TRANSFORMATION = "AES/CBC/PKCS5Padding"
    private val IV_LENGTH_BYTES = 16
    private val Hash_LENGTH_BYTES = 32
    fun encrypt(inputString: String): String {
        val securityKey = "NWRhNjVzZDE2YTVzZHNkYQ=="
        //5da65sd16a5sdsda
        /**
         * First Step
         * @param generatedIV is generated IV with method generateIv (ByteArray)
         * @param iV is subsequence(16 characters) of generatedIV (CharSequence)
         * @param secretKeyByteArray decoded securityKey (ByteArray)
         * @param cipher variable of Cipher
         * @param encryptedFirstStep encrypted data with Cipher
         * @param encryptedFirstStepBase64 base64 encoded encrypted data (AES Encrypt is Done!!)
         */

        val generatedIV = generateIv()
        val iV = Base64.encodeToString(generatedIV, Base64.NO_WRAP).subSequence(0, 16)
        val secretKeyByteArray = Base64.decode(securityKey, Base64.NO_WRAP)
        val skeySpec = SecretKeySpec(secretKeyByteArray, "AES")
        val cipher = Cipher.getInstance(CIPHER_TRANSFORMATION)
        cipher.init(
            Cipher.ENCRYPT_MODE,
            skeySpec,
            IvParameterSpec(iV.toString().toByteArray(Charsets.UTF_8))
        )
        val encryptedFirstStep: ByteArray = cipher.doFinal(inputString.toByteArray(Charsets.UTF_8))
        val encryptedFirstStepBase64 = Base64.encodeToString(encryptedFirstStep, Base64.NO_WRAP)

        /**
         * Second Step
         * @param ivBase64 Base64 encoded of iV in First Step
         * @param hashBase64 subSequence(32 Characters ) of Base64 encoded of generated Hash Random ByteArray
         * @param encryptCancate Cancate String of all Base64 encoded varieble of ivBase64 + hashBase64 + encryptedFirstStepBase64
         * @param encryptFinal final String of Base64 encoded cancate String (encryptCancate)
         */
        val ivBase64 =
            Base64.encodeToString(iV.toString().toByteArray(Charsets.UTF_8), Base64.NO_WRAP)
        val hashBase64 =
            Base64.encodeToString(randomBytes(Hash_LENGTH_BYTES), Base64.NO_WRAP).subSequence(0, 32)

        val builder = StringBuilder()
        builder.append(ivBase64).append(hashBase64).append(encryptedFirstStepBase64)
        val encryptCancate = builder.toString()
        val encryptFinal =
            Base64.encodeToString(encryptCancate.toByteArray(Charsets.UTF_8), Base64.NO_WRAP)
        return encryptFinal
    }

    inline fun <reified T> decrypt(encryptedString: String): T {
        val CIPHER_TRANSFORMATION = "AES/CBC/PKCS5Padding"

        /**
         * First Step
         * @param decodeFirstTime Base64 decode inputed data
         * @param iVSubSequence extraxt iv from decoded String
         * @param iV Base64 decode of subsequence
         * @param ivAfterDecode String Value of iV
         * @param encrypBase64Data extract base64 data from encrypted Data
         * @param encryptData Encrypt Data (AES ENCRYPT DATA IS THIS)
         */
        val decodeFirstTime = String(Base64.decode(encryptedString, Base64.NO_WRAP))
        val iVSubSequence = decodeFirstTime.subSequence(0, 24).toString()
        val iV = Base64.decode(iVSubSequence, Base64.NO_WRAP)
        String(iV)
        val encrypBase64Data = decodeFirstTime.removeRange(0, 56)
        val encryptData = Base64.decode(encrypBase64Data, Base64.NO_WRAP)

        /**
         * Second Step
         */
        val securityBase64 = "NWRhNjVzZDE2YTVzZHNkYQ=="
        val secretKeyByteArray = Base64.decode(securityBase64, Base64.NO_WRAP)
        val skeySpec = SecretKeySpec(secretKeyByteArray, "AES")
        val cipher = Cipher.getInstance(CIPHER_TRANSFORMATION)
        cipher.init(
            Cipher.DECRYPT_MODE,
            skeySpec,
            IvParameterSpec(iV)
        )


        val decrypted = String(cipher.doFinal(encryptData))
        return fromJson(decrypted)

    }

    inline fun <reified T> fromJson(json: String): T {
        return Gson().fromJson(json, object : TypeToken<T>() {}.type)
    }

    /**
     * Creates a random Initialization Vector (IV) of IV_LENGTH_BYTES.
     * @return The byte array of this IV
     * @throws GeneralSecurityException if a suitable RNG is not available
     */
    @Throws(GeneralSecurityException::class)
    fun generateIv(): ByteArray {
        return randomBytes(IV_LENGTH_BYTES)
    }

    @Throws(GeneralSecurityException::class)
    private fun randomBytes(length: Int): ByteArray {
        val random = SecureRandom()
        val b = ByteArray(length)
        random.nextBytes(b)
        return b
    }


}