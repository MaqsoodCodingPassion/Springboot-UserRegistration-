package com.farzi.eng.farziweb;

import org.springframework.util.DigestUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class FarziUtil {

    private static final String DATE_FORMAT_NOW = "MM/dd/yyyy HH:mm:ss";//"dd-MM-yyyy HH:mm:ss";

    public static String getRandomOTP() {
        int sixDigitNumber = 6;
        String numbers = "0123456789";
        Random rndm_method = new Random();

        char[] otp = new char[sixDigitNumber];

        for (int i = 0; i < sixDigitNumber; i++) {
            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        return new String(otp);
    }

    public static String getOTPStatus(String mobileNumber,String otp) {
        try {
            String apiKey = "apikey=" + "Uir5LYgxB18-Ut173EAYbz8o0nWyAVtwkRPh1xsP2b";
            String message = "&message=" + otp;
            String sender = "&sender=" + "TXTLCL";
            String numbers = "&numbers=" + mobileNumber;

            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
            String data = apiKey + numbers + message + sender;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
            rd.close();

            return stringBuffer.toString();
        } catch (Exception e) {
            return "FarziError "+e.getMessage();
        }
    }

    public static String returnHashKey(String pass) {
        byte[] passMod =  DigestUtils.md5Digest(pass.getBytes());
        String hashedPass = new BigInteger(1, passMod).toString(16);

        if (hashedPass.length() < 32) {
            hashedPass = "0" + hashedPass;
        }
        return hashedPass;
    }

    public static String getCurrentDate(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

    public static long timeDifference() {

        long diffMinutes = 0;
        String dateStart = "01/29/2019 20:50:59";
        String dateStop = getCurrentDate();

        //HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_NOW);

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000 % 60;
            diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return diffMinutes;
    }

}
