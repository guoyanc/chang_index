package com.chang.file;

import java.io.File;
import java.util.List;

import com.chang.util.Constant;

public class MediaAction {

	public String getMediaType(List<File> list, String[] patt) {
		String mediaType = Constant.MEDIA_TYPE_OTHERS;
		if(list != null && list.size() > 0) {
			for(int i=0; i<list.size(); i++) {
				if(this.check(list.get(i).getAbsolutePath(), patt)) {
					return Constant.MEDIA_TYPE_FILM;
				}
			}
		}
		return mediaType;
	}
	
	private boolean check(String str, String[] patt) {
		int matchTimes = 0;
		if(patt != null && str != null) {
			for(int i=0; i<patt.length; i++) {
				if(str.toLowerCase().indexOf(patt[i].toLowerCase()) > 0) {
					matchTimes++;
				}
			}
		}
		if(matchTimes > 1) {
			return true;
		}
		else {
			return false;
		}
	}
}
