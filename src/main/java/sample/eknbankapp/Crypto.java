package sample.eknbankapp;

public class Crypto {
    String cryptoName,cryptoValue;

    public String getCryptoName() {
        return cryptoName;
    }

    public String getCryptoValue() {
        return cryptoValue;
    }

    public Crypto(String cryptoName, String cryptoValue) {
        this.cryptoName = cryptoName;
        this.cryptoValue = cryptoValue;
    }

    public void setCryptoName(String cryptoName) {
        this.cryptoName = cryptoName;
    }

    public void setCryptoValue(String cryptoValue) {
        this.cryptoValue = cryptoValue;
    }
}
