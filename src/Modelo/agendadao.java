
package Modelo;

import java.awt.List;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 *
 * @author samuc
 */
public class agendadao {

     public boolean checkLogin(String email, String senha) throws NoSuchAlgorithmException {

        PreparedStatement stmt = null;
        agenda u = new agenda();
        ResultSet rs = null;
        boolean check = false;

        try {
            stmt = con.prepareStatement("SELECT * FROM Tbagenda"
                    + " where email = ? AND senha = ?");
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            if (rs.next()) {
                check = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
        return check;
    }
    // SALVA O USUARIO NO BANCO DE DADOS   ---- C
    public void create(agenda u) throws NoSuchAlgorithmException, SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO Tbagenda (nome,"
                    + "email,senha,telefone,recado) VALUE (?,?,?,?)");
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
             stmt.setString(3, u.getSenha());
            stmt.setString(4, u.getTelefone());
             stmt.setString(5, u.getRecado());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "agenda " + u.getNome()
                    + " Salvo com Sucesso!!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(stmt);
        }
    }
    //ALTERAR O USUARIO NO BANCO DE DADOS   -- U 
    public void update(agenda u) throws NoSuchAlgorithmException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE Tbagenda SET nome = ?,"
                    + "email = ?, senha = ? ,telefone = ?,recado = ? WHERE id = ?");
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
             stmt.setString(3, u.getSenha());
            stmt.setString(4, u.getTelefone());
             stmt.setString(5, u.getRecado());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario " + u.getNome()
                    + " Modificado com Sucesso!!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(stmt);
        }
    }

    //listagem de usuarios na tabela do formulario   ---   R

    public ArrayList<agenda> read() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<agenda> agenda = new ArrayList<agenda>();
        try {
            stmt = con.prepareStatement("SELECT * FROM Tbagenda ORDER BY nome ASC");
            rs = stmt.executeQuery();
            while (rs.next()) {
                agenda agenda = new agenda();
                agenda.setId(rs.getInt("id"));
                agenda.setNome(rs.getString("nome"));
                agenda.setEmail(rs.getString("email"));
                agenda.setSenha(rs.getString("senha"));
                agenda.setTelefone(rs.getString("fone"));
                agenda.setRecado(rs.getString("recado"));
                agenda.add(agenda);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(stmt);
        }
        return (ArrayList<agenda>) agenda;
    }

    public ArrayList<agenda> readPesq(String nome) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<agenda> agenda = new ArrayList<agenda>();
        try {
            stmt = con.prepareStatement("SELECT * FROM Tbagenda WHERE nome LIKE ?");
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                agenda agenda = new agenda();
                agenda.setId(rs.getInt("id"));
                agenda.setNome(rs.getString("nome"));
                agenda.setEmail(rs.getString("email"));
                agenda.setSenha(rs.getString("senha"));
                agenda.setTelefone(rs.getString("fone"));
                agenda.setRecado(rs.getString("recado"));
                agenda.add(agenda);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(stmt);
        }
        return (ArrayList<agenda>) agenda;
    }

        
    //excluir do banco de dados   --- D
    public void delete(agenda u) {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM Tbagenda WHERE id = ?");

            stmt.setInt(1, u.getId());

            if (JOptionPane.showConfirmDialog(null, "Tem certeza que"
                    + " deseja excluir esta agenda", "Exclusão",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Agenda excluída com Sucesso!!");
                stmt.executeUpdate();
            } else {
                JOptionPane.showMessageDialog(null, "A exclusão da agenda Cancelado com Sucesso!!");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(stmt);
        }
    }

    private static class con {

        private static PreparedStatement prepareStatement(String string) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public con() {
        }
    }
}
