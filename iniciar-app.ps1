# ============================================================
#  Salvou CRM - inicia a aplicacao SEM IntelliJ / SEM Maven
#  Compila o codigo e sobe o servidor em http://localhost:8080
# ============================================================
$ErrorActionPreference = "Stop"
Set-Location -Path $PSScriptRoot

# Caminho do Java 17 (o que o IntelliJ baixou)
$env:JAVA_HOME = "C:\Users\User\.jdks\temurin-17.0.19"
$java  = Join-Path $env:JAVA_HOME "bin\java.exe"
$javac = Join-Path $env:JAVA_HOME "bin\javac.exe"

if (-not (Test-Path $java)) {
    Write-Host "ERRO: Java 17 nao encontrado em $env:JAVA_HOME" -ForegroundColor Red
    Read-Host "Pressione Enter para sair"
    exit 1
}

# Monta o classpath com as dependencias do .m2,
# excluindo versoes antigas que conflitam (slf4j 1.7 e spring-core 6.0)
Write-Host "Preparando dependencias..." -ForegroundColor Cyan
$jars = Get-ChildItem -Recurse "$env:USERPROFILE\.m2\repository" -Filter *.jar |
    Where-Object { $_.FullName -notmatch 'sources|javadoc|slf4j-api\\1\.7\.36|spring-core\\6\.0\.10' } |
    ForEach-Object { $_.FullName }
$cp = (@("target\classes") + $jars) -join ';'

# Garante a pasta de saida e compila o codigo atual
if (-not (Test-Path "target\classes")) { New-Item -ItemType Directory -Force "target\classes" | Out-Null }
Write-Host "Compilando o projeto..." -ForegroundColor Cyan
$sources = Get-ChildItem -Recurse "src\main\java" -Filter *.java | ForEach-Object { $_.FullName }
& $javac -parameters -d "target\classes" -cp $cp $sources

# Copia recursos (templates HTML, application.properties) para o classpath
Copy-Item -Recurse -Force "src\main\resources\*" "target\classes\"

Write-Host ""
Write-Host "==================================================" -ForegroundColor Green
Write-Host " Salvou CRM iniciando em: http://localhost:8080" -ForegroundColor Green
Write-Host " (Feche esta janela ou aperte Ctrl+C para parar)" -ForegroundColor Green
Write-Host "==================================================" -ForegroundColor Green
Write-Host ""
& $java -cp $cp com.salvou.crm.SalvouApplication
