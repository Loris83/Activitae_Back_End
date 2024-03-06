package com.activitae.activitae.utils;

import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

import jakarta.servlet.http.HttpServletRequest;

public class UrlChecking {
	
	public static boolean checkUrl(HttpServletRequest request, String url) {
        String serverIP = getServerIPAddress(request);
        
        try {
            URI uri = new URI(url);
            String providedIP = uri.getHost();
            return providedIP != null && providedIP.equals(serverIP);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String getServerIPAddress(HttpServletRequest request) {
        try {
            return InetAddress.getByName(request.getServerName()).getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }
}
