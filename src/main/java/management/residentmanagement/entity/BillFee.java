package management.residentmanagement.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bill_fees")
public class BillFee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bill_id", nullable = false)
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "fee_id", nullable = false)
    private Fee fee;

    @Column(nullable = false)
    private Double quantity;

    @Override
    public String toString() {
        return "BillFee{id=" + id + ", fee=" + fee.getName() + ", quantity=" + quantity + "}";
    }
}