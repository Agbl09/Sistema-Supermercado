package supermercado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoDAO 
{
    private conectaDAO conexao = new conectaDAO();

    // Método para inserir um produto no banco
    public boolean inserirProduto(ProdutoEntity produto) 
    {
        String sql = "INSERT INTO produtos (nome, preco) VALUES (?, ?)";
        
        try (Connection conn = conexao.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) 
        {
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.executeUpdate();
            return true;

        } 
        catch (SQLException e) 
        {
            System.out.println("Erro ao inserir produto: " + e.getMessage());
            return false;
        }
    }
    
    //Método para atualizar um produto no banco
    public boolean atualizarProduto(ProdutoEntity produto)
    {
        String sql = "UPDATE produtos SET nome= ?, preco= ? WHERE id= ?";
        
        try(Connection conn = conexao.connectDB();
            PreparedStatement stmt = conn.prepareStatement(sql))    
        {
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getId());
            stmt.executeUpdate();
            return true;
        }
        
        catch(SQLException e)
        {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
            return false;
        }
    }
    
    //Método para excluir um produto no banco
    public boolean excluirProduto(int id)
    {
       String sql = "DELETE FROM produtos WHERE id= ?";
       
       try(Connection conn = conexao.connectDB();
           PreparedStatement stmt = conn.prepareStatement(sql))
       {
           stmt.setInt(1, id);
           stmt.executeUpdate();
           return true;
       }
       
       catch(SQLException e)
       {
          System.out.println("Erro ao excluir produto: " + e.getMessage());
          return false;
       }
    }

    // Método para verificar se o produto já existe
    public boolean produtoExiste(String nome) 
    {
        String sql = "SELECT * FROM produtos WHERE nome = ?";
        
        try (Connection conn = conexao.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) 
        {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Se encontrar, retorna true

        } 
        catch (SQLException e) 
        {
            System.out.println("Erro ao verificar produto: " + e.getMessage());
            return false;
        }
    }
}
