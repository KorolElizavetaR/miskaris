<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="html" encoding="UTF-8" indent="yes" />

	<xsl:template match="/catalog">
		<html>
			<head>
				<title>Book Catalog</title>
				<style>
					table, th, td {
					border:1px solid black;
					}
				</style>
			</head>
			<body>
				<table>
					<tr>
						<th>ID</th>
						<th>Author</th>
						<th>Title</th>
						<th>Genre</th>
						<th>Price</th>
					</tr>
					<xsl:for-each select="book">
						<tr>
							<td>
								<xsl:value-of select="@id" />
							</td>
							<td>
								<xsl:value-of select="author" />
							</td>
							<td>
								<xsl:value-of select="title" />
							</td>
							<td>
								<xsl:value-of select="genre" />
							</td>
							<td>
								<xsl:value-of select="price" />
								<xsl:text> </xsl:text>
								<xsl:value-of select="price/@currency" />
							</td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>
