package visteon.gestionacces.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ReportExport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdAt;
    private String filePath;

    @ManyToOne
    private User generatedBy;
}
