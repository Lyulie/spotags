package com.ufpb.aps.spotags.repository

import com.ufpb.aps.spotags.model.Album
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AlbumRepository: JpaRepository<Album, Long> {
}