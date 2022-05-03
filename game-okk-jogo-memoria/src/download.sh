for i in $(seq 55);
  do
    echo "---------------------------------------------------------------------------";
    echo "Download imagem on $i.png ...";
    curl "https://rachacuca.com.br/static/memory/set/3/$i.png" --output "imagem-$i.png";
    echo "Download imagem $i.png completo!";
    echo "---------------------------------------------------------------------------";
    echo ""
  done
end
