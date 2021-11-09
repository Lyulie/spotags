package com.ufpb.aps.spotags.model

import javax.persistence.*

@Entity
class Track (
        val trackId: String,
        val name: String,
        tags: List<Tag>,
        @ManyToOne
        val album: Album,
        @ManyToOne
        val artist: Artist,
        val duration: Long,
        val isPlayable: Boolean
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    @ManyToMany
    private var tags = mutableListOf<Tag>()
}