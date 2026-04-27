package com.vin.service;

import com.vin.entity.User;
import com.vin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PdfService {

	private final UserRepository repo;

	public byte[] generate() throws Exception {

		List<User> users = repo.findAll();

		try (PDDocument doc = new PDDocument()) {

			PDPage page = new PDPage();
			doc.addPage(page);

			PDPageContentStream cs = new PDPageContentStream(doc, page);

			float y = 750;

			// Title
			cs.beginText();
			cs.setFont(PDType1Font.HELVETICA_BOLD, 16);
			cs.newLineAtOffset(200, y);
			cs.showText("User Report");
			cs.endText();

			y -= 40;

			for (User u : users) {

				// Text block
				cs.beginText();
				cs.setFont(PDType1Font.HELVETICA, 12);
				cs.newLineAtOffset(50, y);
				cs.showText("Name: " + u.getName());
				cs.endText();

				y -= 15;

				cs.beginText();
				cs.newLineAtOffset(50, y);
				cs.showText("Email: " + u.getEmail());
				cs.endText();

				y -= 15;

				cs.beginText();
				cs.newLineAtOffset(50, y);
				cs.showText("Age: " + u.getAge());
				cs.endText();

				// Image (if exists)
				if (u.getPhoto() != null) {
					try {
						PDImageXObject image = PDImageXObject.createFromByteArray(doc, u.getPhoto(), u.getPhotoName());

						cs.drawImage(image, 400, y - 20, 100, 80);
					} catch (Exception ignored) {
					}
				}

				y -= 100;

				// New page if overflow
				if (y < 100) {
					cs.close();
					page = new PDPage();
					doc.addPage(page);
					cs = new PDPageContentStream(doc, page);
					y = 750;
				}
			}

			cs.close();

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			doc.save(out);

			return out.toByteArray();
		}
	}
}