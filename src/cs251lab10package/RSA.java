package cs251lab10package;
import java.math.BigInteger;
import java.security.SecureRandom;
    

public class RSA {
   private final static int N = 4096; // key length
   private BigInteger privateKey;
   private BigInteger publicKey;
   private BigInteger modulus;

   public RSA() {
	  SecureRandom random = new SecureRandom();
      BigInteger p = BigInteger.probablePrime(N/2, random);
      BigInteger q = BigInteger.probablePrime(N/2, random);
      BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
      modulus    = p.multiply(q);                                  
      publicKey  = new BigInteger("65537");
      privateKey = publicKey.modInverse(phi);
   }


   public String encrypt(String message, String modulus, String publicKey){
	   BigInteger original = new BigInteger(message.getBytes());
	   BigInteger encrypted = original.modPow(new BigInteger(publicKey), new BigInteger(modulus));
	   return new String(encrypted.toString());
   }

   public String decrypt(String message){
	   BigInteger encrypted = new BigInteger(message);
	   BigInteger original = encrypted.modPow(privateKey, modulus);
	   return new String(original.toByteArray()); 
   }

   public String getPublicKey(){
	   return publicKey.toString();
   }
   
   public String getModulus(){
	   return modulus.toString();
   }
}
