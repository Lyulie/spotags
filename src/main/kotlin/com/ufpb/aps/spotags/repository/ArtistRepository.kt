package com.ufpb.aps.spotags.repository

import com.ufpb.aps.spotags.model.Artist
import org.springframework.data.jpa.repository.JpaRepository

interface ArtistRepository: JpaRepository<Artist, Long> {
}