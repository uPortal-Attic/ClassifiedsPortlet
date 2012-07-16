package edu.fortlewis.portlet.ClassifiedsPortlet.service;

import java.util.List;

import edu.fortlewis.portlet.ClassifiedsPortlet.domain.Ad;


public interface AdService {

	public List<Ad> getAd(Long id);
	public List<Ad> getLatestAds(int maxResults);
    public List<Ad> getAdsByUserId(String userid);
    public List<Ad> getAdsForAdmin();
    public void processAd(Ad ad);
    public void delete(Long id);
    public void deleteExpiredAds();
}
