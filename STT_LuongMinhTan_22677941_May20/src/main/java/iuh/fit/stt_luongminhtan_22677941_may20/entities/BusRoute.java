package iuh.fit.stt_luongminhtan_22677941_may20.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "busroute")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BusRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id AUTO_INCREMENT
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "start", nullable = false, length = 255)
    private String start;

    @Column(name = "end", nullable = false, length = 255)
    private String end;

    @Column(name = "price", nullable = false)
    private Integer price;          // giữ nguyên kiểu INT như DB (có thể đổi BigDecimal nếu muốn)

    @Column(name = "priority")
    private Integer priority;       // DEFAULT 0 trong DB

    @Column(name = "stationNo")
    private Integer stationNo;      // có thể nullable
}
