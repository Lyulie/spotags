package com.ufpb.aps.spotags.repository

import com.ufpb.aps.spotags.model.Tag
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TagRepository: JpaRepository<Tag, Long> {
}