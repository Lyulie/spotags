package com.ufpb.aps.spotags.repository

import com.ufpb.aps.spotags.model.Track
import org.springframework.data.jpa.repository.JpaRepository

interface TrackRepository: JpaRepository<Track, Long> {
}