<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
 <xsl:strip-space elements="*"/>
    <xsl:output method="xml" encoding="UTF-8" indent="no"/>
    
    <xsl:key name="genreKey" match="book" use="genre" />

    <xsl:template match="/catalog">
        <library>
            <xsl:for-each select="book[generate-id() = generate-id(key('genreKey', genre)[1])]">
                <genre name="{genre}">
                    <xsl:for-each select="key('genreKey', genre)">
                        <book>
                            <id><xsl:value-of select="@id" /></id>
                            <author><xsl:value-of select="author" /></author>
                            <title><xsl:value-of select="title" /></title>
                            <price currency="{price/@currency}">
                                <xsl:value-of select="price"/>
                            </price>
                        </book>
                    </xsl:for-each>
                </genre>
            </xsl:for-each>
        </library>
    </xsl:template>
</xsl:stylesheet>
