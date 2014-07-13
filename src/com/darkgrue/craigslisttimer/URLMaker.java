package com.darkgrue.craigslisttimer;

import java.util.EnumMap;

public class URLMaker {
	private String cl = ".craigslist.org/";
	private String city = "portland"; // TODO make a function that can covert
										// this to other cities offered by
										// craigslist
	private String url = " ";
	private String query = "search/sga?zoomToPosting=&catAbb=111&query=!!!&minAsk=@@@&maxAsk=$$$&hasPic=555&srchType=666&excats=";

	// This are the fields
	private String category = " "; // 111
	private String search = " "; // !!!
	private String minAsk = " "; // @@@
	private String maxAsk = " "; // $$$
	private String hasPic = " "; // 555
	private String searchTitle = " "; // 666

	/*
	 * sss = all for sale / wanted
	 * 
	 * ata = antiques
	 * 
	 * ppa = appliances
	 * 
	 * ara = arts+crafts
	 * 
	 * pta = auto parts
	 * 
	 * baa = baby+kids
	 * 
	 * bar = barter
	 * 
	 * haa = beauty+health
	 * 
	 * bia = bikes
	 * 
	 * boo = boats
	 * 
	 * bka = books
	 * 
	 * bfa = business
	 * 
	 * cta = cars+trucks
	 * 
	 * ema = cd/dvd/vhs
	 * 
	 * moa = cell phones
	 * 
	 * cla = clothing+accessories
	 * 
	 * cba = collectibles
	 * 
	 * sya = computers
	 * 
	 * ela = electronics
	 * 
	 * gra = farm+garden
	 * 
	 * zip = free stuff
	 * 
	 * fua = furniture
	 * 
	 * gms = garage sales
	 * 
	 * foa = general for sale
	 * 
	 * hsa = household
	 * 
	 * wan = items wanted
	 * 
	 * jwa = jewelry
	 * 
	 * maa = materials
	 * 
	 * mca = motorcycles
	 * 
	 * msa = musical instruments
	 * 
	 * pha = photo+video
	 * 
	 * rva = recreational vehicles
	 * 
	 * sga = sporting goods
	 * 
	 * tia = tickets
	 * 
	 * tla = tools
	 * 
	 * taa = toys+games
	 * 
	 * vga = video gaming
	 */

	public enum Category {
		sss, ata, ppa, ara, pta, baa, bar, haa, bia, boo, bka, bfa, cta, ema, moa, cla, cba, sya, ela, gra, zip, fua, gms, foa, hsa, wan, jwa, maa, mca, msa, pha, rva, sga, tia, tla, taa, vga,
	}

	public EnumMap<Category, String> categoriesMap = new EnumMap<Category, String>(
			Category.class);

	public URLMaker() {
		// Begin the EnumMap for categories...
		this.categoriesMap.put(Category.sss, "All for sale / wanted");
		this.categoriesMap.put(Category.ata, "Antiques");
		this.categoriesMap.put(Category.ppa, "Arts + Crafts");
		this.categoriesMap.put(Category.pta, "Auto parts");
		this.categoriesMap.put(Category.baa, "Baby + Kids");
		this.categoriesMap.put(Category.bar, "Barter");
		this.categoriesMap.put(Category.haa, "Beauty + Health");
		this.categoriesMap.put(Category.bia, "Bikes");
		this.categoriesMap.put(Category.boo, "Boats");
		this.categoriesMap.put(Category.bka, "Books");
		this.categoriesMap.put(Category.bfa, "Business");
		this.categoriesMap.put(Category.cta, "Cars + Trucks");
		this.categoriesMap.put(Category.ema, "CD/DVD/VHS");
		this.categoriesMap.put(Category.moa, "Cell Phones");
		this.categoriesMap.put(Category.cla, "Clothing + Accessories");
		this.categoriesMap.put(Category.cba, "Collectibles");
		this.categoriesMap.put(Category.sya, "Computers");
		this.categoriesMap.put(Category.ela, "Electronics"); 
		this.categoriesMap.put(Category.gra, "Farm + Garden");
		this.categoriesMap.put(Category.zip, "Free Stuff");
		this.categoriesMap.put(Category.fua, "Furniture");
		this.categoriesMap.put(Category.gms, "Garage Sales");
		this.categoriesMap.put(Category.foa, "General For Sale");
		this.categoriesMap.put(Category.hsa, "Household");
		this.categoriesMap.put(Category.wan, "Items Wanted");
		this.categoriesMap.put(Category.jwa, "Jewlery");
		this.categoriesMap.put(Category.maa, "Materials");
		this.categoriesMap.put(Category.mca, "Motorcycles");
		this.categoriesMap.put(Category.msa, "Musical Instruments");
		this.categoriesMap.put(Category.pha, "Photo + Video");
		this.categoriesMap.put(Category.rva, "Recreational Vehicles");
		this.categoriesMap.put(Category.sga, "Sporting Goods");
		this.categoriesMap.put(Category.tia, "Tickets");
		this.categoriesMap.put(Category.tla, "Tools");
		this.categoriesMap.put(Category.taa, "Toys + Games");
		this.categoriesMap.put(Category.vga, "Video Gaming");
	}
	
	public EnumMap<Category, String> getCategoriesMap(){
		return this.categoriesMap;
	}

	public void createQuery() {
		this.search = "asdjflkjaslkdfj;lasd;flakjsdlfjlkasd";
		this.category = Category.sss.toString(); // TODO needs to not be
													// hard-coded...
		this.minAsk = "";
		this.maxAsk = "";
		this.hasPic = "T";
		this.searchTitle = "T";


	}

	public String createQuery(String _search, Category _category,
			String _minAsk, String _maxAsk, boolean _hasPic,
			boolean _searchTitle, String _city) {
		// Make sure that all of the info is safe
		this.search = convertSearch(_search);
		this.category = _category.toString();
		this.minAsk = _minAsk; // TODO this needs to come in pre-verified
		this.maxAsk = _maxAsk;
		this.hasPic = convertBoolean(_hasPic);
		this.searchTitle = convertBoolean(_searchTitle);
		this.city = _city;
		
		// Now build the query
		return getURLfromQuery();
	}

	public String getURLfromQuery() {

		// TODO Change these to GUIDs for Substring search terms
		this.query = this.query.replace("111", this.category);
		this.query = this.query.replace("555", this.hasPic);
		this.query = this.query.replace("666", this.searchTitle);
		this.query = this.query.replace("!!!", this.search);
		this.query = this.query.replace("@@@", this.minAsk);
		this.query = this.query.replace("$$$", this.maxAsk);
		this.url = "http://" + this.city + this.cl + this.query;
		return this.url;
	}

	// ///////////////////////Field Verification Methods///////////
	public String convertSearch(String s) {
		s = s.replace(" ", "+");
		return s;
	}

	public String convertBoolean(boolean b) {
		if (b) {
			return "T";
		} else {
			return "";
		}
	}

	public boolean isNum(String s) {
		// For each character check to see if is in the range of numbers
		return true; // TODO FIXME this is broken as all heck
	}

	public String convertPrice(String s) {
		if (s.compareTo("0") == 0) {
			s = "";
		}
		return s;
	}
	// ///////////////////////End Field Verification Methods///////
}
