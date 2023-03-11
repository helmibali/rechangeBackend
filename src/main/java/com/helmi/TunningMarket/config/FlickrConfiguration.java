package com.helmi.TunningMarket.config;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlickrConfiguration {


/*
    @Bean
    public Flickr getFlickr() throws IOException, ExecutionException, InterruptedException, FlickrException {
        Flickr flickr = new Flickr(apiKey,apiSecret, new REST());
        OAuth10aService service =new ServiceBuilder(apiKey)
                .apiSecret(apiSecret)
                .build(FlickrApi.instance(FlickrApi.FlickrPerm.DELETE));
        final Scanner scanner = new Scanner(System.in);

        final OAuth1RequestToken request =service.getRequestToken();

        final String authUrl = service.getAuthorizationUrl(request);

        System.out.println(authUrl);
        System.out.println("Paste it here >> ");
        final String authVerifier = scanner.nextLine();

        OAuth1AccessToken accessToken = service.getAccessToken(request , authVerifier);

        System.out.println(accessToken.getToken());
        System.out.println(accessToken.getTokenSecret());

        Auth auth = flickr.getAuthInterface().checkToken(accessToken);

        System.out.println("-----------------");
        System.out.println(auth.getToken());
        System.out.println(auth.getTokenSecret());

        return flickr;
    }

 */
    private String apiKey="f04f6d3e4794006ced56e2c0800c726a";
    private String apiSecret="79560874db9d9c85";
    private String appKey="72157720857023342-3b41200aed964143";
    private String appSecret="f7a6bbd5a2e26bf9";

       @Bean
        public Flickr getFlickr(){
       Flickr flickr =new Flickr("f04f6d3e4794006ced56e2c0800c726a","79560874db9d9c85",new REST() );
        Auth auth = new Auth();
        auth.setPermission(Permission.DELETE);
        auth.setToken("72157720857023342-3b41200aed964143");
        auth.setTokenSecret("f7a6bbd5a2e26bf9");
        RequestContext requestContext = RequestContext.getRequestContext();
        requestContext.setAuth(auth);

        flickr.setAuth(auth);
        return flickr;
}
}
