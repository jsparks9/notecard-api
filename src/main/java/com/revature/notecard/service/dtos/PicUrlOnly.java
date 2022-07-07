package com.revature.notecard.service.dtos;

import com.revature.notecard.tables.ProfilePicURL;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PicUrlOnly {
    private String pic;

    public PicUrlOnly(ProfilePicURL profilePicURL) {
        this.pic = profilePicURL.getPic_url();
    }
}
